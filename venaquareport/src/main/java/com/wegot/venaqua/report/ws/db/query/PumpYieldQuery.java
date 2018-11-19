package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.util.DateTimeUtils;
import com.wegot.venaqua.report.ws.RequestInfo;
import com.wegot.venaqua.report.ws.VenAquaReportHelper;
import com.wegot.venaqua.report.ws.db.DBConnection;
import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.db.DBManager;
import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.exception.ReportException;
import com.wegot.venaqua.report.ws.response.gantt.PumpInfo;
import com.wegot.venaqua.report.ws.response.gantt.PumpStatus;
import com.wegot.venaqua.report.ws.response.gantt.PumpYieldResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.StatementConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PumpYieldQuery {
    private final Logger log = LoggerFactory.getLogger(PumpYieldQuery.class);
    private final String QRY_SRT = "SELECT pump_id, site_id, state, cumulative, dt FROM w2_pump_status_log WHERE " +
            "site_id=? AND pump_id=? AND (dt BETWEEN ? and ?) ORDER BY pump_id, dt ASC;";

    public PumpYieldResponse execute(Connection connection, Integer siteId, Date date) throws ProcessException {
        String siteName = DBHelper.getSiteName(connection, siteId);
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
                    log.info("pumpId : {} custName : {} total : {}", pumpId, custName, total);
                }
                return response;
            }
        };
        try {
            PumpYieldResponse response = queryRunner.query(connection, QRY_STR, handler, siteId, fromDate, toDate);

            for (int i = 0; i < response.getPumpInfos().size(); i++) {
                PumpInfo pumpInfo = response.getPumpInfos().get(i);
                List<PumpStatus> statusList = getPumpStatus(connection, siteId, Integer.valueOf(pumpInfo.getId()), date, pumpInfo.getTotalUsage());
                pumpInfo.setPumpStatusList(statusList);
            }
            return response;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch pump details for site id - " + siteId);
        }
    }

    private List<PumpStatus> getPumpStatus(Connection connection, Integer siteId, Integer pumpId, Date date, double weeklyYield) throws SQLException {
        Date from = DateTimeUtils.adjustToDayStart(date);
        Date to = DateTimeUtils.adjustToDayEnd(date);
        StatementConfiguration.Builder builder = new StatementConfiguration.Builder();
        StatementConfiguration configuration = builder.fetchDirection(ResultSet.FETCH_FORWARD)
                .fetchSize(100)
                .maxRows(200)
                .build();
        QueryRunner queryRunner = new QueryRunner(configuration);
        BaseResultSetHandler<List<PumpStatus>> handler = new BaseResultSetHandler<List<PumpStatus>>() {
            @Override
            protected List<PumpStatus> handle() throws SQLException {
                List<PumpStatus> response = new ArrayList<>();
                ResultSet resultSet = getAdaptedResultSet();
                int prevState = 0;
                double startValue = 0;
                boolean flag = false;
                PumpStatus status = null;
                while (resultSet.next()) {
                    int currState = resultSet.getInt(3);
                    if (prevState != currState) {
                        if (currState == 1) {
                            status = new PumpStatus();
                            response.add(status);
                            Timestamp timestamp = resultSet.getTimestamp(5);
                            log.info("Pump Id : {} start : {}", pumpId, timestamp);
                            status.setOnTime(timestamp.getTime());
                            startValue = resultSet.getDouble(4);
                            flag = true;
                        } else {
                            Timestamp timestamp = resultSet.getTimestamp(5);
                            log.info("Pump Id : {} start : {}", pumpId, timestamp);
                            status.setOffTime(timestamp.getTime());
                            double endValue = resultSet.getDouble(4);
                            double yield = endValue - startValue;
                            status.setYieldValue(yield);
                            double val = yield / weeklyYield;
                            status.setYieldPercentage(val * 100);
                            status.setPartialFill(val);
                            log.info("Yield : {} Percentage : {} ", yield, (val * 100));
                            flag = false;
                        }
                    }
                    prevState = currState;
                }
                if (flag) {
                    // handle if bwell not end.
                }
                return response;
            }
        };
        return queryRunner.query(connection, QRY_SRT, handler, siteId, pumpId, from, to);
    }

    public static void main(String[] args) throws ReportException, ProcessException, ClassNotFoundException {
        PumpYieldQuery.class.getClassLoader().loadClass("D:\\Development\\WegotProject\\Report\\WorkingDir\\Report\\venaquareport\\src\\main\\webapp\\WEB-INF\\classes\\resources\\data-sources.xml");
        DBManager dbManager = DBManager.getInstance();
        DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
        Connection connection = dbConnection.getConnection();

        Date date = new Date("2018-07-01");
        System.out.println(date);

        PumpYieldQuery query = new PumpYieldQuery();
        PumpYieldResponse response = query.execute(connection, 4, date);
        dbConnection.releaseConnection(connection);
    }
}
