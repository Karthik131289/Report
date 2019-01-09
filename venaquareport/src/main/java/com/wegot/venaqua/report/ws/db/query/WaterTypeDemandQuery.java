package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.response.usage.WaterTypeDemand;
import com.wegot.venaqua.report.ws.response.usage.WaterTypeDemandResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class WaterTypeDemandQuery {
    private final Logger log = LoggerFactory.getLogger(WaterTypeDemandQuery.class);

    public WaterTypeDemandQuery() {
    }

    public WaterTypeDemandResponse execute(Connection connection, Integer siteId, Date fromDt, Date toDt, Date fromTime, Date toTime) throws ProcessException {
        WaterTypeDemandResponse response = new WaterTypeDemandResponse();
        List<WaterTypeDemand> waterTypeDemandList = response.getWaterTypeDemandList();
        updateWaterType(connection, waterTypeDemandList);
        updateWaterTypeDemand(connection, siteId, fromDt, toDt, fromTime, toTime, waterTypeDemandList);
        return response;
    }

    private List<WaterTypeDemand> updateWaterType(Connection connection, List<WaterTypeDemand> waterTypeDemandList) throws ProcessException {
        final String QRY_WATER_TYPE = "SELECT id, type_name FROM w2_water_type";
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<WaterTypeDemand>> handler = new BaseResultSetHandler<List<WaterTypeDemand>>() {
            @Override
            protected List<WaterTypeDemand> handle() throws SQLException {
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    WaterTypeDemand waterTypeDemand = new WaterTypeDemand();
                    waterTypeDemand.setId(id);
                    waterTypeDemand.setName(name);
                    waterTypeDemand.setType("area");
                    waterTypeDemand.setUnit("l");
                    waterTypeDemand.setValueDecimals(0);
                    waterTypeDemandList.add(waterTypeDemand);
                }
                return waterTypeDemandList;
            }
        };
        try {
            return queryRunner.query(connection, QRY_WATER_TYPE, handler);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch available water types.");
        }
    }

    private List<WaterTypeDemand> updateWaterTypeDemand(Connection connection, Integer siteId, Date fromDt, Date toDt, Date fromTime, Date toTime, List<WaterTypeDemand> waterTypeDemandList) throws ProcessException {
        final String QRY_WATER_TYPE = "SELECT id, type_name FROM w2_water_type";
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<WaterTypeDemand>> handler = new BaseResultSetHandler<List<WaterTypeDemand>>() {
            @Override
            protected List<WaterTypeDemand> handle() throws SQLException {
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {

                }
                return waterTypeDemandList;
            }
        };
        try {
            return queryRunner.query(connection, QRY_WATER_TYPE, handler);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch usage for water types");
        }
    }
}
