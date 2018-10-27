package com.wegot.venaqua.report.ws.db;

import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.exception.ReportException;
import com.wegot.venaqua.report.ws.response.pie.WaterSource;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBHelper {
    private static final Logger log = LoggerFactory.getLogger(DBHelper.class);

    public static Connection getCoreDBConnection() throws ReportException {
        DBManager dbManager = DBManager.getInstance();
        DBConnection dbConnection = dbManager.getDbConnection(DBConnection.COREDB);
        return dbConnection.getConnection();
    }

    public static Integer getSiteId(Connection dbConnection, String siteName) throws ProcessException {
        final String QUERY_STR = "SELECT site_id FROM w2_site_qa1 WHERE site_name=?";
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<Integer> resultHandler = new BaseResultSetHandler<Integer>() {
            @Override
            protected Integer handle() throws SQLException {
                Integer siteId = null;
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    if (siteId == null) {
                        siteId = resultSet.getInt(1);
                    } else
                        throw new SQLException("More than one record found for site Name : " + siteName);
                }

                if (siteId == null)
                    throw new SQLException("Could not find id for site Name : " + siteName);
                return siteId;
            }
        };
        try {
            return queryRunner.query(dbConnection, QUERY_STR, resultHandler, siteName);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to find site details for siteName - " + siteName);
        }
    }

    public static Map<String, Integer> getWaterSourceMap(Connection dbConnection) throws ReportException {
        final String WATER_SOURCE_QUERY = "SELECT * FROM w2_water_source_type;";
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<Map<String, Integer>> resultHandler = new BaseResultSetHandler<Map<String, Integer>>() {
            @Override
            protected Map<String, Integer> handle() throws SQLException {
                Map<String, Integer> waterSources = new HashMap<>();
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    waterSources.put(resultSet.getString(2), resultSet.getInt(1));
                }
                return waterSources;
            }
        };
        try {
            return queryRunner.query(dbConnection, WATER_SOURCE_QUERY, resultHandler);
        } catch (SQLException e) {
            throw new ReportException(e);
        }
    }

    public static List<WaterSource> getWaterSourceObject(Connection dbConnection) throws ProcessException {
        final String WATER_SOURCE_QUERY = "SELECT * FROM w2_water_source_type;";
        QueryRunner queryRunner = new QueryRunner();
        ResultSetHandler<List<WaterSource>> resultHandler = new BaseResultSetHandler<List<WaterSource>>() {
            @Override
            protected List<WaterSource> handle() throws SQLException {
                List<WaterSource> waterSources = new ArrayList<>();
                ResultSet resultSet = getAdaptedResultSet();
                while (resultSet.next()) {
                    String name = resultSet.getString(2);
                    WaterSource waterSource = new WaterSource();
                    waterSource.setName(name);
                    waterSources.add(waterSource);
                }
                return waterSources;
            }
        };
        try {
            return queryRunner.query(dbConnection, WATER_SOURCE_QUERY, resultHandler);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch available water sources.");
        }
    }


}
