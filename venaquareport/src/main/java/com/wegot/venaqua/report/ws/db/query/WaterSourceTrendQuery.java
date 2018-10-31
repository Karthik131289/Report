package com.wegot.venaqua.report.ws.db.query;

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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WaterSourceTrendQuery {
    private final Logger log = LoggerFactory.getLogger(WaterSourceTrendQuery.class);

    public WaterSourceTrendQuery() {
    }

    public WaterSourceTrendResponse execute(Connection connection, String siteName, Date fromDate, Date toDate) throws ProcessException {
        Integer siteId = DBHelper.getSiteId(connection, siteName);
        return getTrend(connection, siteId, fromDate, toDate);
    }

    private WaterSourceTrendResponse getTrend(Connection connection, Integer siteId, Date fromDate, Date toDate) throws ProcessException {
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
                    waterSources.add(waterSource);
                }
                return waterSourceTrendResponse;
            }
        };
        try {
            return queryRunner.query(connection, WATER_SOURCE_QUERY, resultHandler);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch available water sources.");
        }
    }

    private void applyTrend() throws ProcessException {

    }
}
