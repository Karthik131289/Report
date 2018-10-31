package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ProcessException;
import com.wegot.venaqua.report.ws.response.sparkline.WaterSourceTrendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;

public class WaterSourceTrendQuery {
    private final Logger log = LoggerFactory.getLogger(WaterSourceTrendQuery.class);

    public WaterSourceTrendQuery() {
    }

    public WaterSourceTrendResponse execute(Connection connection, String siteName, Date date) throws ProcessException {
        Integer siteId = DBHelper.getSiteId(connection, siteName);
        return null;
    }
}
