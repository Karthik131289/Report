package com.wegot.venaqua.report.ws.db.query;

import com.wegot.venaqua.report.ws.db.DBHelper;
import com.wegot.venaqua.report.ws.exception.ReportException;
import com.wegot.venaqua.report.ws.response.gantt.PumpYieldResponse;

import java.sql.Connection;
import java.util.Date;

public class PumpYieldQuery {

    public PumpYieldResponse execute(Connection connection, String siteName, Date date) throws ReportException {
        Integer siteId = DBHelper.getSiteId(connection, siteName);
        return null;
    }
}
