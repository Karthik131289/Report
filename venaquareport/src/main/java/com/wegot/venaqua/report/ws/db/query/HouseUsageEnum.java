package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.ws.response.bubble.HighUsersResponse;
import com.wegot.venaqua.report.ws.response.bubble.HouseInfo;
import com.wegot.venaqua.report.ws.response.tree.BlockInfo;
import com.wegot.venaqua.report.ws.response.tree.BlockLevelUsageResponse;
import org.apache.commons.dbutils.BaseResultSetHandler;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public enum HouseUsageEnum {
    BLOCKLEVEL("BlockLevel", new BaseResultSetHandler<BlockLevelUsageResponse>() {
        @Override
        protected BlockLevelUsageResponse handle() throws SQLException {
            BlockLevelUsageResponse response = new BlockLevelUsageResponse();
            ResultSet resultSet = getAdaptedResultSet();
            BlockInfo block = null;
            while (resultSet.next()) {
                String blockName = resultSet.getString(2);
                if (block == null) {
                    block = response.createAndAddBlock(blockName);
                }
                else if (!blockName.equals(block.getName())) {
                    block = response.createAndAddBlock(blockName);
                }
                String houseName = resultSet.getString(3);
                Integer usage = resultSet.getInt(5);
                block.createAndAddHouse(houseName, usage.doubleValue());
            }
            return response;
        }
    }),
    HIGHUSERS("HighUsers", new BaseResultSetHandler<HighUsersResponse>() {
        @Override
        protected HighUsersResponse handle() throws SQLException {
            HighUsersResponse response = new HighUsersResponse();
            List<HouseInfo> houses = response.getHouses();
            ResultSet resultSet = getAdaptedResultSet();
            while (resultSet.next()) {
                String blockName = resultSet.getString(2);
                String houseName = resultSet.getString(3);
                String name = blockName + "-" + houseName;
                Integer usage = resultSet.getInt(5);
                HouseInfo house = new HouseInfo(name, usage.doubleValue());
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