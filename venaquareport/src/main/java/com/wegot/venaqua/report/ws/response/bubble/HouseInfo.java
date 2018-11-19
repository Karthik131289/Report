package com.wegot.venaqua.report.ws.response.bubble;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseInfo {
    private String name;
    @JsonProperty("size")
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
