package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.util.DateTimeUtils;
import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.response.waterMap.SiteDayUsage;
import com.wegot.venaqua.report.ws.response.waterMap.WaterMapResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SiteWaterMapQuery {
    private final Logger log = LoggerFactory.getLogger(SiteWaterMapQuery.class);
    private final static String QRY_STR = "select id, time_group, usage_quantity from w2_site_timely_usage where " +
            "site_id = ? and (time_group>=? and time_group<?) Order By time_group Asc";

    public SiteWaterMapQuery() {
    }

    public WaterMapResponse execute(Connection connection, Integer siteId, Date fromDate, Date toDate) throws ProcessException {
        String siteName = DBHelper.getSiteName(connection, siteId);
        WaterMapResponse waterMapResponse = getDayUsage(connection, siteId, fromDate, toDate);
        return waterMapResponse;
    }

    private WaterMapResponse getDayUsage(Connection connection, Integer siteId, Date fromDate, Date toDate) throws ProcessException {
        //final String QUERY_STR = "select id, agg_total, dt from w2_apart_day_total where apart_id=? and (dt>=? and dt<?);";
        final String QUERY_STR = "call sp_site_day_usage(?, ?, ?)";
        final int INTERVAL = 2;
        Date from = DateTimeUtils.adjustToDayStart(fromDate);
        Date to = DateTimeUtils.adjustToDayEnd(toDate);

        QueryRunner queryRunner = new QueryRunner();
        BaseResultSetHandler<WaterMapResponse> handler = new BaseResultSetHandler<WaterMapResponse>() {
            @Override
            protected WaterMapResponse handle() throws SQLException {
                SimpleDateFormat format = new SimpleDateFormat("MMM-yyyy");
                WaterMapResponse response = new WaterMapResponse();
                List<SiteDayUsage> siteDayUsageList = response.getSiteDayUsageList();
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    SiteDayUsage dayUsage = new SiteDayUsage();
                    Date dt = resultSet.getDate(1);
                    int total = resultSet.getInt(2);
                    int date = dt.getDate();
                    int month = dt.getMonth();
                    String monthYear = format.format(dt);

                    dayUsage.setDayUsage(total);
                    dayUsage.setDate(date);
                    dayUsage.setMonth(month + 1);
                    dayUsage.setMonthYear(monthYear);
                    dayUsage.setInterval(INTERVAL);
                    //dayUsage.setValues(getDayBreakUpdetails(connection, siteId, dt));
                    siteDayUsageList.add(dayUsage);
                }
                return response;
            }
        };
        try {
            return queryRunner.query(connection, QUERY_STR, handler, siteId, from, to);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch water map details.");
        }
    }

    private List<Double> getRandomNumberList(int count, int max) {
        List<Double> numberList = new ArrayList<>();
        double avg = max / 24;
        for (int i = 0; i < count; i++) {
            numberList.add(avg);
        }
        return numberList;
    }

    private List<Double> getDayBreakUpdetails(Connection connection, Integer siteId, Date date) throws SQLException {
        log.debug("fetching day breakup for site usage...");
        Date from = DateTimeUtils.adjustToDayStart(date);
        Date to = DateTimeUtils.adjustToDayEnd(date);
        QueryRunner queryRunner = new QueryRunner();
        BaseResultSetHandler<List<Double>> handler = new BaseResultSetHandler<List<Double>>() {
            @Override
            protected List<Double> handle() throws SQLException {
                List<Double> hourlyUsage = new ArrayList<>();
                ResultSet resultSet = super.getAdaptedResultSet();
                while (resultSet.next()) {
                    double usage = resultSet.getDouble(3);
                    java.sql.Date time = resultSet.getDate(2);
                    usage = usage < 0 ? 0 : usage;
                    hourlyUsage.add(usage);
                }
                return hourlyUsage;
            }
        };
        return queryRunner.query(connection, QRY_STR, handler, siteId, from, to);
    }

    private List<Double> getDayBreakUpdetails2(Connection connection, Integer siteId, Date fromDate, Date toDate) throws SQLException {
        log.debug("fetching day breakup for site usage...");
        Date from = DateTimeUtils.adjustToDayStart(fromDate);
        Date to = DateTimeUtils.adjustToDayEnd(toDate);
        QueryRunner queryRunner = new QueryRunner();
        BaseResultSetHandler<List<DayInfo>> handler = new BaseResultSetHandler<List<DayInfo>>() {
            @Override
            protected List<DayInfo> handle() throws SQLException {
                List<DayInfo> dayInfos = new ArrayList<>();
                ResultSet resultSet = super.getAdaptedResultSet();
                while (resultSet.next()) {
                    double usage = resultSet.getDouble(3);
                    java.sql.Date time = resultSet.getDate(2);
                    usage = usage < 0 ? 0 : usage;
                    dayInfos.add(usage);
                }
                return dayInfos;
            }
        };
        return queryRunner.query(connection, QRY_STR, handler, siteId, from, to);
    }

    class DayInfo {
        private Date date;
        private List<Double> hourlyData = new ArrayList<>();

        public DayInfo() {
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public List<Double> getHourlyData() {
            return hourlyData;
        }

        public void setHourlyData(List<Double> hourlyData) {
            this.hourlyData = hourlyData;
        }

        public void addHourlyData(Double usage) {
            this.hourlyData.add(usage);
        }
    }
}
