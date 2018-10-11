package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ReportException;
import com.wegot.venaqua.report.ws.response.WaterSource;
import com.wegot.venaqua.report.ws.response.WaterSourceUsageResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SiteUsageByWaterSourceQuery {
    private Connection connection;

    public SiteUsageByWaterSourceQuery(Connection connection) {
        this.connection = connection;
    }

    public WaterSourceUsageResponse execute(String siteName, Date fromDate, Date toDate) throws ReportException {
        Integer siteId = DBHelper.getSiteId(this.connection, siteName);
        List<WaterSource> waterSourceList = DBHelper.getWaterSourceObject(this.connection);
        WaterSourceUsageResponse waterSourceUsageResponse = new WaterSourceUsageResponse(waterSourceList);

        double wtpUsage = getWTPUsage(siteId, fromDate, toDate);

        for (WaterSource source : waterSourceUsageResponse.getWaterSourceList()) {
            if("WTP".equals(source.getName()))
                source.setUsage(wtpUsage);
        }

        return waterSourceUsageResponse;
    }

    private double getWTPUsage(Integer siteId, Date fromDate, Date toDate) throws ReportException {
        final String QUERY_STR = "SELECT t1.id, t1.wtp_id, t1.day_total, t1.dt FROM w2_wtp_component_day_total t1 " +
                "JOIN (SELECT DISTINCT (wtp_id) FROM w2_wtp WHERE site_id=?) t2 " +
                "ON t1.wtp_id=t2.wtp_id AND (t1.dt BETWEEN ? and ? )";
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<Double> resultHandler = new BaseResultSetHandler<Double>() {
            Double res = 0.0;
            @Override
            protected Double handle() throws SQLException {
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    res = res + resultSet.getDouble(3);
                }
                return res;
            }
        };
        try {
            return queryRunner.query(this.connection, QUERY_STR, resultHandler, siteId, fromDate, toDate);
        } catch (SQLException e) {
            throw new ReportException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println( new Date(2018,03,01,00,00,00).getTime());
        System.out.println( new Date(2018,03,30,00,00,00).getTime());
        //System.out.println( new Date("").getTime());
    }
}
