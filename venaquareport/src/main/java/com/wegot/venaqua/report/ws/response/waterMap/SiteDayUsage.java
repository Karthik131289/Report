package com.wegot.venaqua.report.ws.response.waterMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SiteDayUsage {
    private int month;
    private int date;
    private String monthYear;
    private Date dt;
    private double dayUsage;
    private int interval;
    private List<Double> values = new ArrayList<>();

    public SiteDayUsage() {
    }

    public SiteDayUsage(List<Double> values) {
        this.values = values;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    @JsonIgnore()
    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public double getDayUsage() {
        return dayUsage;
    }

    public void setDayUsage(double dayUsage) {
        this.dayUsage = dayUsage;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public void addValue(Double value) {
        this.values.add(value);
    }
}
