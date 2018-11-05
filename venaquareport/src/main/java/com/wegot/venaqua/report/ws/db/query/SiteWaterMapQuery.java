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

    public SiteWaterMapQuery() {
    }

    public WaterMapResponse execute(Connection connection, Integer siteId, Date fromDate, Date toDate) throws ProcessException {
        String siteName = DBHelper.getSiteName(connection, siteId);
        return getUsage(connection, siteId, fromDate, toDate);
    }

    private WaterMapResponse getUsage(Connection connection, Integer siteId, Date fromDate, Date toDate) throws ProcessException {
        final String QUERY_STR = "select id, agg_total, dt from w2_apart_day_total where apart_id=? and (dt>=? and dt<?);";
        final int INTERVAL = 1;
        Date from = DateTimeUtils.subDays(fromDate, 1);
        Date to = DateTimeUtils.addDays(toDate, 1);

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
                    int total = resultSet.getInt(2);
                    Date dt = resultSet.getDate(3);
                    int date = dt.getDate();
                    int month = dt.getMonth();
                    String monthYear = format.format(dt);

                    dayUsage.setDayUsage(total);
                    dayUsage.setDate(date);
                    dayUsage.setMonth(month + 1);
                    dayUsage.setMonthYear(monthYear);
                    dayUsage.setInterval(INTERVAL);
                    dayUsage.setValues(getRandomNumberList(24, total));
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
}
