package com.wegot.venaqua.report.ws.db;

import com.wegot.venaqua.report.ws.db.datasource.DataSource;
import com.wegot.venaqua.report.ws.db.datasource.DataSourcePool;
import com.wegot.venaqua.report.ws.exception.ReportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DBConnectionPool {
    private final Logger log = LoggerFactory.getLogger(DBConnectionPool.class);
    private DataSourcePool dataSourcePool;
    private List<DBConnection> dbConnectionList = new ArrayList<>();

    public DBConnectionPool(DataSourcePool dataSourcePool) throws ReportException {
        this.dataSourcePool = dataSourcePool;
        initDBConnections(this.dataSourcePool);
    }

    private void initDBConnections(DataSourcePool dataSourcePool) throws ReportException {
        List<DataSource> dataSourceList = dataSourcePool.getDataSourceList();
        for (DataSource dataSource : dataSourceList) {
            DBConnection dbConnection = new DBConnection(dataSource);
            dbConnectionList.add(dbConnection);
        }
    }

    public DataSourcePool getDataSourcePool() {
        return dataSourcePool;
    }

    public List<DBConnection> getDbConnectionList() {
        return dbConnectionList;
    }

    public DBConnection getDBConnection(String name) {
        for (DBConnection dbConnection : dbConnectionList) {
            if (dbConnection.getName().equals(name))
                return dbConnection;
        }
        return null;
    }
}
