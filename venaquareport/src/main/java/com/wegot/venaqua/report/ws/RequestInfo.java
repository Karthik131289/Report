package com.wegot.venaqua.report.ws;

import java.util.Date;

public class RequestInfo {
    private String uid;
    private String chartType;
    private Date fromDate;
    private Date toDate;

    public RequestInfo() {

    }
    public RequestInfo(String uid, String chartType, Date fromDate, Date toDate) {
        this.uid = uid;
        this.chartType = chartType;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
