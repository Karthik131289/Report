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
        final String QRY_STR = "SELECT id, pump_id, cust_name, block_id, site_id FROM w2_pumps WHERE site_id=?";
        QueryRunner queryRunner = new QueryRunner();
        BaseResultSetHandler<PumpYieldResponse> handler = new BaseResultSetHandler<PumpYieldResponse>() {
            @Override
            protected PumpYieldResponse handle() throws SQLException {
                PumpYieldResponse response = new PumpYieldResponse();
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    PumpInfo pumpInfo = new PumpInfo();
                    response.addPumpInfo(pumpInfo);

                    Integer id = resultSet.getInt(1);
                    pumpInfo.setId(Integer.toString(id));
                    String pumpId = resultSet.getString(2);
                    pumpInfo.setPumpId(pumpId);
                    pumpInfo.setLabel(resultSet.getString(3));

                    List<PumpStatus> statusList = getPumpStatus(connection, siteId, id, date);
                    pumpInfo.setPumpStatusList(statusList);
                }
                return response;
            }
        };
        try {
            return queryRunner.query(connection, QRY_STR, handler, siteId);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch pump details for site id - " + siteId);
        }
    }

    private List<PumpStatus> getPumpStatus(Connection connection, Integer siteId, Integer id, Date date) throws SQLException {
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
        return queryRunner.query(connection, QRY_SRT, handler, siteId, id, from, to);
    }
}
