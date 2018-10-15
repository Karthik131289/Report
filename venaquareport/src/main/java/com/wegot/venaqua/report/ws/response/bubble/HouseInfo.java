package com.wegot.venaqua.report.ws.response.bubble;

public class HouseInfo {
    private String name;
    private double usage;

    public HouseInfo() {
    }

    public HouseInfo(String name, double usage) {
        this.name = name;
        this.usage = usage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }
}
