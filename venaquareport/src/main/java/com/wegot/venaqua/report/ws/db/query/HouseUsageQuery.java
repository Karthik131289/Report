package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.util.DateTimeUtils;
import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.response.bubble.Children;
import com.wegot.venaqua.report.ws.response.bubble.HighUsersResponse;
import com.wegot.venaqua.report.ws.response.bubble.HouseInfo;
import com.wegot.venaqua.report.ws.response.tree.BlockInfo;
import com.wegot.venaqua.report.ws.response.tree.BlockLevelUsageResponse;
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

public class HouseUsageQuery<T> {
    private final Logger log = LoggerFactory.getLogger(HouseUsageQuery.class);

    public HouseUsageQuery() {
    }

    public T execute(Connection connection, HouseUsageEnum usageEnum, Integer siteId, Date fromDate, Date toDate) throws ProcessException {
        T response = getUsage(connection, usageEnum, siteId, fromDate, toDate);
        return response;
    }

    private T getUsage(Connection connection, HouseUsageEnum usageEnum, Integer siteId, Date fromDate, Date toDate) throws ProcessException {
        final String QUERY_STR = "SELECT t1.site_id, t1.block_name, t2.cust_name, t3.apart_id, t3.total from w2_block t1 inner join w2_apart_master t2 on t1.site_id=? and t2.block_id=t1.id INNER JOIN (select apart_id, sum(agg_total) as total from w2_apart_day_total where dt>=? and dt<? group by apart_id) t3 on t3.apart_id=t2.id;";
        QueryRunner queryRunner = new QueryRunner();
        Date from = DateTimeUtils.subDays(fromDate, 1);
        Date to = DateTimeUtils.addDays(toDate, 1);
        long dateDiff = DateTimeUtils.findDateDiff(from, to);
        try {
            //ResultSetHandler<T> handler = usageEnum.getHandler();
            if(usageEnum.getName().equals("HighUsers")) {
                ResultSetHandler<HighUsersResponse> handler = new HighUsersUsageHandler(dateDiff);
                return ((T) queryRunner.query(connection, QUERY_STR, handler, siteId, from, to));
            } else {
                ResultSetHandler<BlockLevelUsageResponse> handler = new BlockLevelUsageHandler(dateDiff);
                return ((T) queryRunner.query(connection, QUERY_STR, handler, siteId, from, to));
            }
            //return queryRunner.query(connection, QUERY_STR, handler, siteId, from, to);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new ProcessException("Unable to fetch houses day usage.");
        } catch (ClassCastException e) {
            throw new ProcessException(e);
        }
    }


    class BlockLevelUsageHandler extends BaseResultSetHandler<BlockLevelUsageResponse> {
        private long dateRange = 0;

        public BlockLevelUsageHandler(long dateRange) {
            this.dateRange = dateRange;
        }

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
                Double usage = resultSet.getDouble(5);
                usage = sanitizeUsage(usage);
                block.createAndAddHouse(houseName, usage.doubleValue());
            }
            return response;
        }

        private double sanitizeUsage(double usage) {
            double result = usage<0? 0 : usage;
            // todo check this condition
            result = result>(5000*dateRange) ? 0 : result;
            return result;
        }
    }

    class HighUsersUsageHandler extends BaseResultSetHandler<HighUsersResponse> {
        private long dateRange = 0;

        public HighUsersUsageHandler(long dateRange) {
            this.dateRange = dateRange;
        }
        @Override
        protected HighUsersResponse handle() throws SQLException {
            HighUsersResponse response = new HighUsersResponse();
            List<Children> childrenList = response.getChildren();
            Children children = new Children();
            childrenList.add(children);
            List<HouseInfo> houses = children.getHouses();
            ResultSet resultSet = getAdaptedResultSet();
            while (resultSet.next()) {
                String houseName = resultSet.getString(3);
                Double usage = resultSet.getDouble(5);
                usage = sanitizeUsage(usage);
                HouseInfo house = new HouseInfo(houseName, usage.doubleValue());
                houses.add(house);
            }
            return response;
        }

        private double sanitizeUsage(double usage) {
            double result = usage<0? 0 : usage;
            // todo check this condition
            result = result>(5000*dateRange) ? 0 : result;
            return result;
        }
    }
}
