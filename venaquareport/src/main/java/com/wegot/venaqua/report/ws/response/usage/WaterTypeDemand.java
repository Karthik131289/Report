package com.wegot.venaqua.report.ws.response.usage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WaterTypeDemand {
    private int id;
    private String name;
    @JsonProperty("data")
    private List<Double> usage = new ArrayList<>();
    private String unit;
    private String type;
    private int valueDecimals;

    public WaterTypeDemand() {
    }

    public WaterTypeDemand(String name, List<Double> usage, String unit, String type, int valueDecimals) {
        this.name = name;
        this.usage = usage;
        this.unit = unit;
        this.type = type;
        this.valueDecimals = valueDecimals;
    }

    @JsonIgnore()
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getUsage() {
        return usage;
    }

    public void setUsage(List<Double> usage) {
        this.usage = usage;
    }

    public void addUsage(Double usage) {
        this.usage.add(usage);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValueDecimals() {
        return valueDecimals;
    }

    public void setValueDecimals(int valueDecimals) {
        this.valueDecimals = valueDecimals;
    }
}
