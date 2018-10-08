package com.wegot.venaqua.report.ws.db.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "data-sources")
public class DataSourcePool {
    public static final String DATA_SOURCES_XML = "/resources/data-sources.xml";
    private final Logger log = LoggerFactory.getLogger(DataSourcePool.class);
    List<DataSource> dataSourceList;

    public DataSourcePool() {
        this.dataSourceList = new ArrayList<>();
    }

    public DataSourcePool(List<DataSource> dataSourceList) {
        this.dataSourceList = dataSourceList;
    }

    @XmlElement(name = "data-source")
    public List<DataSource> getDataSourceList() {
        return dataSourceList;
    }

    public DataSource getDataSource(String name) {
        for (DataSource ds : this.dataSourceList) {
            if (ds.getName().equals(name))
                return ds;
        }
        log.debug("Could not find data-source with name - '" + name + "'.");
        return null;
    }

    public void setDataSourceList(List<DataSource> dataSourceList) {
        this.dataSourceList = dataSourceList;
    }
    public void addDataSource(DataSource dataSource) {
        if (this.dataSourceList == null) {
            this.dataSourceList = new ArrayList<>();
        }
        this.dataSourceList.add(dataSource);
    }

    @Override
    public String toString() {
        return dataSourceList.toString();
    }
}
