package com.wegot.venaqua.report.ws.db;

import com.wegot.venaqua.report.ws.db.datasource.DataSource;
import com.wegot.venaqua.report.ws.exception.ReportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    public static final String COREDB = "coredb";
    private final Logger log = LoggerFactory.getLogger(DBConnection.class);
    private String name;
    private DataSource dataSource;
    private transient List freeConnections = new ArrayList();
    private transient List inuseConnections = new ArrayList();
    private long lastUsage;

    public DBConnection(DataSource dataSource) throws ReportException {
        this.dataSource = dataSource;
        this.name = dataSource.getName();
        initConnection(dataSource);
    }

    private void initConnection(DataSource dataSource) throws ReportException {
        try {
            initInstance();
            String driver = dataSource.getConnectionDriver();
            Class.forName(driver);

            checkInactivity();
            if (freeConnections.size() == 0) {
                Connection connection = createConnection();
                freeConnections.add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new ReportException("Could not find / load driver class for data-source - '" + dataSource.getName() + "'."
                    + e.getMessage(), e);
        } catch (SQLException e) {
            throw new ReportException("Could not create database connection for data-source - '" + dataSource.getName() + "'."
                    + e.getMessage(), e);
        }
    }

    private Connection getDBConnection() throws ReportException {
        try {
            checkInactivity();
            Connection connection = null;
            if (freeConnections.size() == 0)
                connection = createConnection();
            else
                connection = (Connection) freeConnections.remove(freeConnections.size() - 1);
            inuseConnections.add(connection);
            logStatus(connection, false);

            return connection;
        } catch (SQLException e) {
            throw new ReportException(e);
        }
    }

    private Connection createConnection() throws SQLException {
        String url = dataSource.getUrl() + "?autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
        String username = dataSource.getUsername();
        String password = dataSource.getPassword();
        return DriverManager.getConnection(url, username, password);
    }

    public synchronized void releaseConnection(Connection con) {
        initInstance();
        if (inuseConnections.remove(con)) {
            freeConnections.add(con);
            logStatus(con, true);
        }
        else {
            log.debug("Connection release error. Attempting to release un-managed connection " + con.hashCode());
        }
    }

    public synchronized void releaseConnection(Connection con, boolean corrupted) {
        initInstance();
        if (inuseConnections.remove(con)) {
            if (corrupted) {
                try {
                    purgeInfo("Discarding possibly corrupted connection");
                    con.close();
                }
                catch (SQLException e) { }
            }
            else
                freeConnections.add(con);
        }
        else
            log.debug("Connection release error. Attempting to release un-managed connection " + con.hashCode());
        logStatus(con, true);
    }

    public String getName() {
        return name;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() throws ReportException {
        Connection connection = null;
        try {
            connection = getDBConnection();
            if (connection.isClosed()) {
                releaseConnection(connection, true);
                connection = getDBConnection();
            }
        } catch (SQLException e) {
            releaseConnection(connection, true);
            connection = getDBConnection();
        }
        return connection;
    }

    private void initInstance() {
        if (freeConnections == null) {
            freeConnections = new ArrayList();
        }
        if (inuseConnections == null) {
            inuseConnections = new ArrayList();
        }
    }

    private void checkInactivity() {
        long currentUsage = System.currentTimeMillis();
        if (lastUsage != 0 && currentUsage - lastUsage > dataSource.getInactivityTimeoutMS()) {
            closeFreeConnections(null);
        }
        lastUsage = currentUsage;
    }

    private void purgeInfo(String s) {
        log.debug("[Cleanup]" + s);
    }

    private SQLException closeInUseConnections(SQLException toThrow) {
        if (inuseConnections != null) {
            for (int i = 0; i < inuseConnections.size(); ++i) {
                Connection con = (Connection) inuseConnections.get(i);
                try {
                    con.close();
                }
                catch (SQLException e) {
                    toThrow = e;
                }
            }
            inuseConnections.clear();
        }
        return toThrow;
    }

    private SQLException closeFreeConnections(SQLException toThrow) {
        if (freeConnections != null) {
            if (freeConnections.size() > 0)
                purgeInfo("Purging connections on timeout");
            for (int i = 0; i < freeConnections.size(); ++i) {
                Connection con = (Connection) freeConnections.get(i);
                try {
                    con.close();
                }
                catch (SQLException e) {
                    toThrow = e;
                }
            }
            freeConnections.clear();
        }
        return toThrow;
    }

    private synchronized void closeAllConnections() throws SQLException {
        SQLException toThrow = null;
        toThrow = closeInUseConnections(toThrow);
        toThrow = closeFreeConnections(toThrow);
        logStatus(null, true);
        if (toThrow != null) {
            throw toThrow;
        }
    }

    private void logStatus(Connection con, boolean release) {
        String str = "";
        if (con != null) {
            if (release) {
                str = "Releasing connection ";
            }
            else {
                str = "Acquiring connection ";
            }
            str += con.hashCode() + ". ";
        }
        str += "In use = " + inuseConnections.size() + " free = " + freeConnections.size();
        log.debug(str);
    }
}
