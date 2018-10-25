package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.util.DateTimeUtils;
import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ReportException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class HouseUsageQuery<T> {
    public HouseUsageQuery() {
    }

    public T execute(Connection connection, HouseUsageEnum usageEnum, String siteName, Date fromDate, Date toDate) throws ReportException {
        Integer siteId = DBHelper.getSiteId(connection, siteName);
        T response = getUsage(connection, usageEnum, siteId, fromDate, toDate);
        return response;
    }

    private T getUsage(Connection connection, HouseUsageEnum usageEnum, Integer siteId, Date fromDate, Date toDate) throws ReportException {
        final String QUERY_STR = "SELECT t1.site_id, t1.block_name, t2.cust_name, t3.apart_id, t3.total from w2_block t1 inner join w2_apart_master t2 on t1.site_id=? and t2.block_id=t1.id INNER JOIN (select apart_id, sum(agg_total) as total from w2_apart_day_total where dt>=? and dt<? group by apart_id) t3 on t3.apart_id=t2.id;";
        QueryRunner queryRunner = new QueryRunner();
        Date from = DateTimeUtils.subDays(fromDate, 1);
        Date to = DateTimeUtils.addDays(toDate, 1);
        try {
            ResultSetHandler<T> handler = usageEnum.getHandler();
            return queryRunner.query(connection, QUERY_STR, handler, siteId, from, to);
        } catch (SQLException e) {
            throw new ReportException(e);
        } catch (ClassCastException e) {
            throw new ReportException(e);
        }
    }
}
