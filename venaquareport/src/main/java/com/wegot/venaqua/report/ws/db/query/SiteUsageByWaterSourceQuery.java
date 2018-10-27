package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.util.DateTimeUtils;
import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.response.pie.WaterSource;
import com.wegot.venaqua.report.ws.response.pie.WaterSourceUsageResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class SiteUsageByWaterSourceQuery {
    private final Logger log = LoggerFactory.getLogger(SiteUsageByWaterSourceQuery.class);
    public SiteUsageByWaterSourceQuery() { }

    public WaterSourceUsageResponse execute(Connection connection, String siteName, Date fromDate, Date toDate) throws ProcessException {
        Integer siteId = DBHelper.getSiteId(connection, siteName);
        List<WaterSource> waterSourceList = DBHelper.getWaterSourceObject(connection);
        WaterSourceUsageResponse waterSourceUsageResponse = new WaterSourceUsageResponse(waterSourceList);

        double wtpUsage = getUsage(WaterSourceEnum.WTP, connection, siteId, fromDate, toDate);
        double tankerUsage = getUsage(WaterSourceEnum.TANKER, connection, siteId, fromDate, toDate);
        double groundUsage = getUsage(WaterSourceEnum.GROUND, connection, siteId, fromDate, toDate);
        double domesticUsage = getUsage(WaterSourceEnum.DOMESTIC, connection, siteId, fromDate, toDate);
        double municipalUsage = getUsage(WaterSourceEnum.MUNICIPAL, connection, siteId, fromDate, toDate);
        double flushUsage = getUsage(WaterSourceEnum.FLUSH, connection, siteId, fromDate, toDate);
        // TODO: 13-Oct-18 uncomment below line to query Rain water usage. Add query in Enum.
        //double rainWaterUsage = getUsage(WaterSourceEnum.RAINWATER, connection, siteId, fromDate, toDate);

        for (WaterSource source : waterSourceUsageResponse.getWaterSourceList()) {
             if(WaterSourceEnum.WTP.getDbName().equals(source.getName())) {
                source.setUsage(wtpUsage);
             } else if(WaterSourceEnum.TANKER.getDbName().equals(source.getName())) {
                 source.setUsage(tankerUsage);
             } else if(WaterSourceEnum.GROUND.getDbName().equals(source.getName())) {
                 source.setUsage(groundUsage);
             } else if(WaterSourceEnum.DOMESTIC.getDbName().equals(source.getName())) {
                 source.setUsage(domesticUsage);
             } else if(WaterSourceEnum.MUNICIPAL.getDbName().equals(source.getName())) {
                 source.setUsage(municipalUsage);
             } else if(WaterSourceEnum.FLUSH.getDbName().equals(source.getName())) {
                 source.setUsage(flushUsage);
             } else if(WaterSourceEnum.RAINWATER.getDbName().equals(source.getName())) {
                 source.setUsage(0.0);
             }
        }

        return waterSourceUsageResponse;
    }

    private double getUsage(WaterSourceEnum waterSourceEnum, Connection connection, Integer siteId, Date fromDate, Date toDate) throws ProcessException {
        final String QUERY_STR = waterSourceEnum.getUsageQry();
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<Double> resultHandler = new BaseResultSetHandler<Double>() {
            Double res = 0.0;
            @Override
            protected Double handle() throws SQLException {
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    res = res + resultSet.getDouble(waterSourceEnum.getUsageColumn());
                }
                return res;
            }
        };
        try {
            Date from = DateTimeUtils.subDays(fromDate, 1);
            Date to = DateTimeUtils.addDays(toDate, 1);
            return queryRunner.query(connection, QUERY_STR, resultHandler, siteId, from, to);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch usage details for water source - " + waterSourceEnum.name());
        }
    }

    public static void main(String[] args) throws ParseException {
        Date date = new Date(61480665000000L);
        System.out.println(date);

        Date fromDt = new Date(2018, 03, 01, 00, 00, 00);
        System.out.println( fromDt.getTime());

        Date toDt = new Date(2018, 03, 30, 00, 00, 00);
        System.out.println( toDt.getTime());
        //System.out.println( new Date("").getTime());

        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();
        System.out.println("The Java Date is: " + javaDate.toString());

        java.sql.Date sqlDate = new java.sql.Date(javaTime);
        System.out.println("The SQL DATE is: " + sqlDate.toString());

        java.sql.Time sqlTime = new java.sql.Time(javaTime);
        System.out.println("The SQL TIME is: " + sqlTime.toString());

        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
        System.out.println("The SQL TIMESTAMP is: " + sqlTimestamp.toString());

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        String frmDtStr = "2018-APR-01 00:00:00";
        fromDt = dateFormatGmt.parse(frmDtStr);
        System.out.println(fromDt);
        System.out.println(fromDt.getTime());
        System.out.println( "From DT in UTC : " + dateFormatGmt.format(fromDt));
        String toDtStr = "2018-APR-30 23:59:59";
        toDt = dateFormatGmt.parse(toDtStr);
        System.out.println(toDt);
        System.out.println(toDt.getTime());
        System.out.println( "To DT in UTC : " + dateFormatGmt.format(toDt));

        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
    }
}
