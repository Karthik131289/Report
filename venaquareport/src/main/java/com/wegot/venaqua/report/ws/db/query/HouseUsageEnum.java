package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.ws.response.bubble.HighUsersResponse;
import com.wegot.venaqua.report.ws.response.bubble.HouseInfo;
import com.wegot.venaqua.report.ws.response.tree.BlockLevelUsageResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public enum HouseUsageEnum {
    BLOCKLEVEL("BlockLevel", new BaseResultSetHandler<BlockLevelUsageResponse>() {
        BlockLevelUsageResponse response = new BlockLevelUsageResponse();
        @Override
        protected BlockLevelUsageResponse handle() throws SQLException {
            ResultSet resultSet = getAdaptedResultSet();
            while (resultSet.next()) {

            }
            return response;
        }
    }),
    HIGHUSERS("HighUsers" , new BaseResultSetHandler<HighUsersResponse>() {
        @Override
        protected HighUsersResponse handle() throws SQLException {
            HighUsersResponse response = new HighUsersResponse();
            List<HouseInfo> houses = response.getHouses();
            ResultSet resultSet = getAdaptedResultSet();
            while (resultSet.next()) {
                String blockName = resultSet.getString(2);
                String houseName = resultSet.getString(3);
                Long usage = resultSet.getLong(5);
                HouseInfo house = new HouseInfo(blockName+"-"+houseName, usage.doubleValue());
                houses.add(house);
            }
            return response;
        }
    });

    HouseUsageEnum(String name, ResultSetHandler handler) {
        this.name = name;
        this.handler = handler;
    }

    public String getName() {
        return name;
    }

    public ResultSetHandler getHandler() {
        return handler;
    }

    private String name;
    private ResultSetHandler handler;
}
