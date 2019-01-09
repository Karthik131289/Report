package com.wegot.venaqua.report.ws;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wegot.venaqua.report.json.TimeParser;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class RequestInfo {
    private Integer siteId;
    private String chartType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fromDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date toDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @JsonDeserialize(using = TimeParser.class)
    private Date fromTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @JsonDeserialize(using = TimeParser.class)
    private Date toTime;
    private Date date;

    public RequestInfo() {

    }

    @JsonCreator()
    public RequestInfo(@JsonProperty(value = "siteId") Integer siteId,
                       @JsonProperty(value = "chartType") String chartType,
                       @JsonProperty(value = "fromDate") Date fromDate,
                       @JsonProperty(value = "toDate") Date toDate,
                       @JsonProperty(value = "fromTime") Date fromTime,
                       @JsonProperty(value = "toTime") Date toTime,
                       @JsonProperty(value = "date") Date date) {
        this.siteId = siteId;
        this.chartType = chartType;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.date = date;
    }

    @XmlElement(required = true)
    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
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

    public String toJson() {
        StringBuilder builder = new StringBuilder();
        builder.append("{").append("\"siteId\":").append(siteId);
        builder.append(",").append("\"chartType\":").append("\"").append(chartType).append("\"");
        if (fromDate != null)
            builder.append(",").append("\"fromDate\":").append("\"").append(fromDate).append("\"");
        if (toDate != null)
            builder.append(",").append("\"toDate\":").append("\"").append(toDate).append("\"");
        if (fromTime != null)
            builder.append(",").append("\"fromTime\":").append("\"").append(fromTime).append("\"");
        if (toTime != null)
            builder.append(",").append("\"toTime\":").append("\"").append(toTime).append("\"");
        if (date != null)
            builder.append(",").append("\"date\":").append("\"").append(date).append("\"");
        builder.append("}");
        return builder.toString();
    }
}
