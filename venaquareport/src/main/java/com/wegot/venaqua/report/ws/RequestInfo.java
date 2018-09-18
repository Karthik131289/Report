package com.wegot.venaqua.report.ws;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class RequestInfo {
    private String uid;
    private String chartType;
    private Date fromDate;
    private Date toDate;
    private Date fromTime;
    private Date toTime;
    private Date date;

    public RequestInfo() {

    }
    public RequestInfo(String uid, String chartType, Date fromDate, Date toDate, Date fromTime, Date toTime, Date date) {
        this.uid = uid;
        this.chartType = chartType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.date = date;
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

    @XmlElement(required = false)
    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @XmlElement(required = false)
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @XmlElement(required = false)
    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    @XmlElement(required = false)
    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    @XmlElement(required = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
