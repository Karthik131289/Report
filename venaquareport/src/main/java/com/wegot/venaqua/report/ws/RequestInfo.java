package com.wegot.venaqua.report.ws;

import javax.xml.bind.annotation.XmlElement;
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

    @XmlElement(required = true)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @XmlElement(required = true)
    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    @XmlElement(required = true)
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @XmlElement(required = true)
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
