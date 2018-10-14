package com.wegot.venaqua.report.ws.db;

import com.wegot.venaqua.report.util.XMLUtils;
import com.wegot.venaqua.report.ws.db.datasource.DataSourcePool;
import com.wegot.venaqua.report.ws.exception.ReportException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBManager {
    private final Logger log = LoggerFactory.getLogger(DBManager.class);
    private static DBManager dbManager;
    private DataSourcePool dataSourcePool;
    private DBConnectionPool dbConnectionPool;

    private DBManager() throws ReportException {
        log.debug("Initializing DBManager");
        initDataSourceConfig();
        initDBConnectionPool();
    }

    public static DBManager getInstance() throws ReportException {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    private void initDataSourceConfig() throws ReportException {
        log.debug("Initializing DataSource");
        this.dataSourcePool = readDataSourceConfig();
        log.info("reading data-source config done.");
    }

    private void initDBConnectionPool() throws ReportException {
        log.debug("Initializing DB connections");
        this.dbConnectionPool = new DBConnectionPool(this.dataSourcePool);
        log.info("creating DB connections done.");
    }

    public DataSourcePool readDataSourceConfig() throws ReportException {
        InputStream resource = this.getClass().getClassLoader().getResourceAsStream(DataSourcePool.DATA_SOURCES_XML);
        if (resource == null)
            throw new ReportException("could not find or load resource - '" + DataSourcePool.DATA_SOURCES_XML + "'.");

        Unmarshaller unMarshaller = XMLUtils.getUnMarshaller(DataSourcePool.class);
        return XMLUtils.<DataSourcePool>parseXML(unMarshaller, resource);
    }

    public DataSourcePool getDataSourcePool() {
        return this.dataSourcePool;
    }

    public void setDataSourcePool(DataSourcePool dataSourcePool) {
        log.debug("Overwriting existing data-source set : " + this.dataSourcePool);
        log.debug("Newer data-source set : " + dataSourcePool);
        this.dataSourcePool = dataSourcePool;
    }

    public DBConnectionPool getDbConnectionPool() {
        return dbConnectionPool;
    }

    public void setDbConnectionPool(DBConnectionPool dbConnectionPool) {
        this.dbConnectionPool = dbConnectionPool;
    }

    public DBConnection getDbConnection(String name) {
        return dbConnectionPool.getDBConnection(name);
    }

    public void test() throws ReportException {
        DBManager instance = DBManager.getInstance();
        DBConnection dbConnection = instance.getDbConnection(DBConnection.COREDB);
        Connection connection = dbConnection.getConnection();

        QueryRunner queryRunner = new QueryRunner();
        String qry = "SELECT * FROM w2_water_source_type;";
        ResultSetHandler<List<List<String>>> rsh = new ResultSetHandler<List<List<String>>>() {
            @Override
            public List<List<String>> handle(ResultSet resultSet) throws SQLException {
                final ResultSetMetaData meta = resultSet.getMetaData();
                final int columnCount = meta.getColumnCount();
                final List<List<String>> rowList = new LinkedList<List<String>>();
                while (resultSet.next())
                {
                    final List<String> columnList = new LinkedList<String>();
                    rowList.add(columnList);

                    for (int column = 1; column <= columnCount; ++column)
                    {
                        final Object value = resultSet.getObject(column);
                        columnList.add(String.valueOf(value));
                    }
                }

                return rowList;
            }
        };
        Object query = null;
        try {
            query = queryRunner.query(connection, qry, rsh);
            System.out.println("query : " + query);
        } catch (SQLException e) {
            throw new ReportException(e);
        }
    }

    public static void main(String args[]) {
        JAXBContext jaxbContext = null;
        try {
            ClassLoader classLoader = DBManager.class.getClassLoader();
            classLoader.loadClass("D:\\Development\\WegotProject\\Report\\WorkingDir\\Report\\venaquareport\\src\\main\\webapp\\WEB-INF\\classes\\resources\\data-sources.xml");

            jaxbContext = JAXBContext.newInstance(DataSourcePool.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            DataSourcePool dataSourcePool = (DataSourcePool) unmarshaller.unmarshal(new File("D:\\Development\\WegotProject\\Report\\WorkingDir\\Report\\venaquareport\\src\\main\\webapp\\WEB-INF\\classes\\resources\\data-sources.xml"));
            System.out.println(dataSourcePool);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
