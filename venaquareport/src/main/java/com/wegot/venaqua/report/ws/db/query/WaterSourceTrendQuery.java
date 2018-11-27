package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.util.DateTimeUtils;
import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.response.sparkline.WaterSourceTrend;
import com.wegot.venaqua.report.ws.response.sparkline.WaterSourceTrendResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WaterSourceTrendQuery {
    private final Logger log = LoggerFactory.getLogger(WaterSourceTrendQuery.class);

    public WaterSourceTrendQuery() {
    }

    public WaterSourceTrendResponse execute(Connection connection, Integer siteId, Date fromDate, Date toDate) throws ProcessException {
        String siteName = DBHelper.getSiteName(connection, siteId);
        Date from = DateTimeUtils.adjustToDayStart(fromDate);
        Date to = DateTimeUtils.adjustToDayEnd(toDate);
        Info info = prepareWeeklyInfo(from, to);
        return getTrend(connection, siteId, from, to, info);
    }

    private WaterSourceTrendResponse getTrend(Connection connection, Integer siteId, Date fromDate, Date toDate, Info info) throws ProcessException {
        final String WATER_SOURCE_QUERY = "SELECT * FROM w2_water_source_type;";
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<WaterSourceTrendResponse> resultHandler = new BaseResultSetHandler<WaterSourceTrendResponse>() {
            @Override
            protected WaterSourceTrendResponse handle() throws SQLException {
                WaterSourceTrendResponse waterSourceTrendResponse = new WaterSourceTrendResponse();
                List<WaterSourceTrend> waterSources = waterSourceTrendResponse.getWaterSources();
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    String name = resultSet.getString(2);

                    WaterSourceTrend waterSource = new WaterSourceTrend();
                    waterSource.setWaterSource(name);
                    waterSource = applyTrend(waterSource, connection, siteId, fromDate, toDate, info);
                    waterSources.add(waterSource);
                }
                return waterSourceTrendResponse;
            }
        };
        try {
            log.debug("fetching available water sources...");
            return queryRunner.query(connection, WATER_SOURCE_QUERY, resultHandler);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch available water sources.");
        }
    }

    private WaterSourceTrend applyTrend(WaterSourceTrend waterSource, Connection connection, Integer siteId, Date fromDate, Date toDate, Info info) throws SQLException {
        WaterSourceTrendEnum waterSourceEnum = getWaterSourceEnum(waterSource);
        QueryRunner queryRunner = new QueryRunner();
        BaseResultSetHandler<WaterSourceTrend> resultHandler = new BaseResultSetHandler<WaterSourceTrend>() {
            @Override
            protected WaterSourceTrend handle() throws SQLException {
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    double dayUsage = resultSet.getDouble(waterSourceEnum.getUsageColumn());
                    Timestamp dt = resultSet.getTimestamp(waterSourceEnum.getDateColumn());
                    checkAndUpdateUsage(info, dayUsage, dt);
                }

                List<WeeklyInfo> weeklyInfo = info.getWeeklyInfo();
                double totalUsage = 0;
                double finalWeekUsage = 0;
                int weekCount = 0;
                for (WeeklyInfo week : weeklyInfo) {
                    weekCount++;
                    Double usage = week.getTotalUsage();
                    log.trace("Weekly Usage : {} for Dt range - {} to {}", usage, week.getStartDate(), week.getEndDate());
                    totalUsage = totalUsage + usage;
                    waterSource.addWeeklyTrend(usage);
                    if (week.isFinalWeek()) {
                        finalWeekUsage = usage;
                        applyFinalWeekTrend(week, waterSource);
                        log.trace("Final Week Trend : {}", waterSource.getFinalWeekTrend());
                    }
                    log.debug("reset weekly usage to 0");
                    week.resetTotalUsage();
                }
                double avgUsage = (totalUsage == 0.0D ? 0.0D : (totalUsage / weekCount));
                double performance = (totalUsage == 0.0D ? 0.0D : Math.round(((finalWeekUsage - avgUsage) / totalUsage) * 100.0D));
                waterSource.setPerformance(performance);

                return waterSource;
            }
        };
        log.trace("Fetching usage for water source : {}", waterSourceEnum.getDbName());
        return queryRunner.query(connection, waterSourceEnum.getQuery(), resultHandler, siteId, fromDate, toDate);
    }

    private void applyFinalWeekTrend(WeeklyInfo week, WaterSourceTrend waterSource) {
        List<DayInfo> finalWeekInfo = week.getFinalWeekTrend();
        List<Double> finalWeekTrend = waterSource.getFinalWeekTrend();
        for (DayInfo dayInfo : finalWeekInfo) {
            finalWeekTrend.add(dayInfo.getUsage());
            dayInfo.resetTotalUsage();
        }
    }

    private WaterSourceTrendEnum getWaterSourceEnum(WaterSourceTrend waterSourceTrend) {
        String name = waterSourceTrend.getWaterSource();
        if (WaterSourceTrendEnum.WTP.getDbName().equals(name))
            return WaterSourceTrendEnum.WTP;
        else if (WaterSourceTrendEnum.TANKER.getDbName().equals(name))
            return WaterSourceTrendEnum.TANKER;
        else if (WaterSourceTrendEnum.DOMESTIC.getDbName().equals(name))
            return WaterSourceTrendEnum.DOMESTIC;
        else if (WaterSourceTrendEnum.FLUSH.getDbName().equals(name))
            return WaterSourceTrendEnum.FLUSH;
        else if (WaterSourceTrendEnum.GROUND.getDbName().equals(name))
            return WaterSourceTrendEnum.GROUND;
        else if (WaterSourceTrendEnum.MUNICIPAL.getDbName().equals(name))
            return WaterSourceTrendEnum.MUNICIPAL;
        else if (WaterSourceTrendEnum.RAINWATER.getDbName().equals(name))
            return WaterSourceTrendEnum.RAINWATER;
        else
            return null;
    }

    private void checkAndUpdateUsage(Info info, double dayUsage, Timestamp dt) {
        List<WeeklyInfo> weeklyInfo = info.getWeeklyInfo();
        Date date = new Date(dt.getTime());
        log.trace("Record date - date : {} usage : {}", date, dayUsage);
        for (WeeklyInfo week : weeklyInfo) {
            if (DateTimeUtils.isWithinRange(date, week.getStartDate(), week.getEndDate())) {
                log.trace("Week range : {} to {}", week.getStartDate(), week.getEndDate());
                log.trace("Weekly total usage(previous) : {}", week.getTotalUsage());
                week.addTotalUsage(dayUsage);
                log.trace("Weekly total usage(current) : {}", week.getTotalUsage());
                if (week.isFinalWeek()) {
                    updateFinalWeekTrend(week, date, dayUsage);
                }
            }
        }
    }

    private void updateFinalWeekTrend(WeeklyInfo week, Date date, double dayUsage) {
        List<DayInfo> finalWeekTrend = week.getFinalWeekTrend();
        for (DayInfo day : finalWeekTrend) {
            if (DateTimeUtils.isSameDate(date, day.getDate())) {
                log.trace("Set final week trend for Date : {} usage as {}", date, dayUsage);
                day.addUsage(dayUsage);
            }
        }
    }

    private Info prepareWeeklyInfo(Date fromDate, Date toDate) {
        log.trace("preparing week info...");
        Info info = new Info();

        log.trace("Requested Date range : {} to {}", fromDate, toDate);
        Date calcFromDate = DateTimeUtils.getStartDateOfWeek(fromDate);
        Date calcToDate = DateTimeUtils.getEndDateOfWeek(toDate);
        log.trace("Calculated Date range : {} to {}", calcFromDate, calcToDate);

        long between = DateTimeUtils.findDateDiff(calcFromDate, calcToDate);
        log.trace("Total No of days : {}", between);
        info.setWeekCount(between / 7);
        info.setDayCount(between % 7);
        log.trace("Total No of weeks and days : {} & {}", info.getWeekCount(), info.getDayCount());
        long totalWeek = info.getDayCount() == 0 ? info.getWeekCount() : info.getWeekCount() + 1;

        Calendar cal = Calendar.getInstance();
        cal.setTime(calcFromDate);
        for (int weekCounter = 0, dayInc = 7; weekCounter < totalWeek; weekCounter++) {
            WeeklyInfo weeklyInfo = new WeeklyInfo();
            weeklyInfo.setStartDate(DateTimeUtils.getStartDateOfWeek(cal));
            weeklyInfo.setEndDate(DateTimeUtils.getEndDateOfWeek(cal));
            if (weekCounter == totalWeek - 1) {
                weeklyInfo.setFinalWeek(true);
                prepareFinalWeekInfo(weeklyInfo, toDate);
            }

            info.addWeeklyInfo(weeklyInfo);
            cal.add(Calendar.DATE, dayInc);
        }
        return info;
    }

    private void prepareFinalWeekInfo(WeeklyInfo weeklyInfo, Date toDate) {
        log.trace("preparing final week day info...");
        Date fromDate = DateTimeUtils.getStartDateOfWeek(toDate);
        log.trace("FinalWeek Start : " + fromDate);
        log.trace("FinalWeek End : " + toDate);
        long days = DateTimeUtils.findDateDiff(fromDate, toDate);
        log.trace("No of days : " + days);
        Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        List<DayInfo> finalWeekTrend = weeklyInfo.getFinalWeekTrend();
        for (int i = 0, dayInc = 1; i < days; i++) {
            DayInfo dayInfo = new DayInfo();
            finalWeekTrend.add(dayInfo);

            Date date = cal.getTime();
            log.trace("Creating DayInfo for " + date);
            dayInfo.setDate(date);
            dayInfo.setUsage(0.0D);

            cal.add(Calendar.DATE, dayInc);
        }
    }

    class Info {
        private List<WeeklyInfo> weeklyInfo = new ArrayList<>();
        private long weekCount;
        private long dayCount;

        public Info() {
        }

        public List<WeeklyInfo> getWeeklyInfo() {
            return weeklyInfo;
        }

        public void setWeeklyInfo(List<WeeklyInfo> weeklyInfo) {
            this.weeklyInfo = weeklyInfo;
        }

        public void addWeeklyInfo(WeeklyInfo weeklyInfo) {
            this.weeklyInfo.add(weeklyInfo);
        }

        public long getWeekCount() {
            return weekCount;
        }

        public void setWeekCount(long weekCount) {
            this.weekCount = weekCount;
        }

        public long getDayCount() {
            return dayCount;
        }

        public void setDayCount(long dayCount) {
            this.dayCount = dayCount;
        }
    }

    class WeeklyInfo {
        private Date startDate;
        private Date endDate;
        private Double totalUsage = 0D;
        private boolean finalWeek = false;
        private List<DayInfo> finalWeekTrend = new ArrayList<>();

        public WeeklyInfo() {
        }

        public WeeklyInfo(Date startDate, Date endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public Double getTotalUsage() {
            return totalUsage;
        }

        public void setTotalUsage(Double totalUsage) {
            this.totalUsage = totalUsage;
        }

        public void addTotalUsage(Double dayUsage) {
            this.totalUsage = totalUsage + dayUsage;
        }

        public void resetTotalUsage() {
            this.totalUsage = 0D;
        }

        public boolean isFinalWeek() {
            return finalWeek;
        }

        public void setFinalWeek(boolean isfinalWeek) {
            this.finalWeek = isfinalWeek;
        }

        public List<DayInfo> getFinalWeekTrend() {
            return finalWeekTrend;
        }

        public void setFinalWeekTrend(List<DayInfo> finalWeekTrend) {
            this.finalWeekTrend = finalWeekTrend;
        }
    }

    class DayInfo {
        private Date date;
        private double usage = 0.0D;

        public DayInfo() {
        }

        public DayInfo(Date date, double usage) {
            this.date = date;
            this.usage = usage;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public double getUsage() {
            return usage;
        }

        public void setUsage(double usage) {
            this.usage = usage;
        }

        public void addUsage(Double dayUsage) {
            this.usage = this.usage + dayUsage;
        }

        public void resetTotalUsage() {
            this.usage = 0.0D;
        }
    }
}
