package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.util.DateTimeUtils;
import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.response.gantt.PumpInfo;
import com.wegot.venaqua.report.ws.response.gantt.PumpStatus;
import com.wegot.venaqua.report.ws.response.gantt.PumpYieldResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PumpYieldQuery {
    private final Logger log = LoggerFactory.getLogger(PumpYieldQuery.class);

    public PumpYieldResponse execute(Connection connection, String siteName, Date date) throws ProcessException {
        Integer siteId = DBHelper.getSiteId(connection, siteName);
        return preparePumpYieldResponse(connection, siteId, date);
    }

    private PumpYieldResponse preparePumpYieldResponse(Connection connection, Integer siteId, Date date) throws ProcessException {
        //final String QRY_STR = "SELECT id, pump_id, cust_name, block_id, site_id FROM w2_pumps WHERE site_id=?";
        final String QRY_STR = "select t1.bwell_id, sum(t1.agg_total) as total, t2.cust_name, t2.pump_id from w2_bwell_day_total t1 inner join w2_pumps t2 on t2.site_id=? and t2.id=t1.bwell_id and (dt>=? and dt<?) group by t1.bwell_id";
        Date fromDate = DateTimeUtils.getStartDateOfWeek(date);
        Date toDate = DateTimeUtils.getEndDateOfWeek(date);
        QueryRunner queryRunner = new QueryRunner();
        BaseResultSetHandler<PumpYieldResponse> handler = new BaseResultSetHandler<PumpYieldResponse>() {
            @Override
            protected PumpYieldResponse handle() throws SQLException {
                PumpYieldResponse response = new PumpYieldResponse();
                ResultSet resultSet = getAdaptedResultSet();
                int id;
                double total;
                String custName;
                String pumpId;
                while (resultSet.next()) {
                    id = resultSet.getInt(1);
                    total = resultSet.getDouble(2);
                    custName = resultSet.getString(3);
                    pumpId = resultSet.getString(4);

                    PumpInfo pumpInfo = new PumpInfo();
                    response.addPumpInfo(pumpInfo);

                    pumpInfo.setId(Integer.toString(id));
                    pumpInfo.setPumpId(pumpId);
                    pumpInfo.setLabel(custName);
                    pumpInfo.setTotalUsage(total);

                    List<PumpStatus> statusList = getPumpStatus(connection, siteId, id, date);
                    pumpInfo.setPumpStatusList(statusList);
                }
                return response;
            }
        };
        try {
            return queryRunner.query(connection, QRY_STR, handler, siteId, fromDate, toDate);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch pump details for site id - " + siteId);
        }
    }

    private List<PumpStatus> getPumpStatus(Connection connection, Integer siteId, Integer pumpId, Date date) throws SQLException {
        final String QRY_SRT = "SELECT pump_id, site_id, state, cumulative, dt FROM w2_pump_status_log WHERE " +
                "site_id=? AND pump_id=? AND (dt BETWEEN ? and ?) ORDER BY pump_id, dt ASC;";

        Date from = DateTimeUtils.adjustToDayStart(date);
        Date to = DateTimeUtils.adjustToDayEnd(date);
        QueryRunner queryRunner = new QueryRunner();
        BaseResultSetHandler<List<PumpStatus>> handler = new BaseResultSetHandler<List<PumpStatus>>() {
            @Override
            protected List<PumpStatus> handle() throws SQLException {
                List<PumpStatus> response = new ArrayList<>();
                ResultSet resultSet = getAdaptedResultSet();
                int prevState = 0;
                PumpStatus status;
                while (resultSet.next()) {
                    int currState = resultSet.getInt(3);
                    if (prevState != currState) {

                    }
                }
                return response;
            }
        };
        return queryRunner.query(connection, QRY_SRT, handler, siteId, pumpId, from, to);
    }
}
