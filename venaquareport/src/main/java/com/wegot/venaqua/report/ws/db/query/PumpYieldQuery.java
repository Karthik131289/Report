package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.util.DateTimeUtils;
import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.response.gantt.PumpYieldResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class PumpYieldQuery {
    private final Logger log = LoggerFactory.getLogger(PumpYieldQuery.class);

    public PumpYieldResponse execute(Connection connection, String siteName, Date date) throws ProcessException {
        Integer siteId = DBHelper.getSiteId(connection, siteName);
        return null;
    }

    private PumpYieldResponse getPumpYield(Connection connection, Integer siteId, Date date) throws ProcessException {
        final String QRY_SRT = "SELECT t1.id, t1.pump_id, t1.cust_name, t1.block_id, t2.site_id, t2.state, t2.cumulative, t2.dt " +
                "from w2_pumps t1 INNER JOIN ( SELECT *  FROM w2_pump_status_log WHERE site_id=? and (dt BETWEEN ? and ?)) t2" +
                " on t1.id = t2.pump_id and t1.site_id=?;";
        Date from = DateTimeUtils.adjustToDayStart(date);
        Date to = DateTimeUtils.adjustToDayEnd(date);
        QueryRunner queryRunner = new QueryRunner();
        BaseResultSetHandler<PumpYieldResponse> handler = new BaseResultSetHandler<PumpYieldResponse>() {
            @Override
            protected PumpYieldResponse handle() throws SQLException {
                return null;
            }
        };

            return queryRunner.query(connection, QRY_SRT, handler, siteId, from, to, siteId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
