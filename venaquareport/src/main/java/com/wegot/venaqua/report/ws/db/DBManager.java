package com.wegot.venaqua.report.ws.db;

import com.wegot.venaqua.report.util.XMLUtils;
import com.wegot.venaqua.report.ws.db.datasource.DataSourcePool;
import com.wegot.venaqua.report.ws.exception.ReportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
