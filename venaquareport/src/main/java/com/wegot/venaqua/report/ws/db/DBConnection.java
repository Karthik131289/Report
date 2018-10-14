package com.wegot.venaqua.report.ws.db;

import com.wegot.venaqua.report.ws.db.datasource.DataSource;
import com.wegot.venaqua.report.ws.exception.ReportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static final String COREDB = "coredb";
    private final Logger log = LoggerFactory.getLogger(DBConnection.class);
    private String name;
    private DataSource dataSource;
    private Connection connection;

    public DBConnection(DataSource dataSource) throws ReportException {
        this.dataSource = dataSource;
        this.name = dataSource.getName();
        initConnection(dataSource);
    }

    private void initConnection(DataSource dataSource) throws ReportException {
        this.connection = getDBConnection(dataSource);
    }

    public void resetConnection(DataSource dataSource) throws ReportException {
        this.connection = getDBConnection(dataSource);
        this.name = dataSource.getName();
        this.dataSource = dataSource;
    }

    private Connection getDBConnection(DataSource dataSource) throws ReportException {
        try {
            String driver = dataSource.getConnectionDriver();
            Class.forName(driver);

            String url = dataSource.getUrl() + "?autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
            String username = dataSource.getUsername();
            String password = dataSource.getPassword();
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new ReportException("Could not find / load driver class for data-source - '" + dataSource.getName() + "'."
                    + e.getMessage(), e);
        } catch (SQLException e) {
            throw new ReportException("Could not create database connection for data-source - '" + dataSource.getName() + "'."
                    + e.getMessage(), e);
        }
    }

    public String getName() {
        return name;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() throws ReportException {
        try {
            if (connection.isClosed())
                this.connection = getDBConnection(this.dataSource);

        } catch (SQLException e) {
            this.connection = getDBConnection(this.dataSource);
        }
        return connection;
    }
}
