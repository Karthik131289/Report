package com.wegot.venaqua.report.ws.response.usage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WaterTypeDemandResponse {
    @JsonProperty("xData")
    private List<String> timeIntervalList = new ArrayList<>();
    @JsonProperty("datasets")
    private List<WaterTypeDemand> waterTypeDemandList = new ArrayList<>();

    public WaterTypeDemandResponse() {
    }

    public List<String> getTimeIntervalList() {
        return timeIntervalList;
    }

    public void setTimeIntervalList(List<String> timeIntervalList) {
        this.timeIntervalList = timeIntervalList;
    }

    public void addTimeInterval(String time) {
        this.timeIntervalList.add(time);
    }

    public List<WaterTypeDemand> getWaterTypeDemandList() {
        return waterTypeDemandList;
    }

    public void setWaterTypeDemandList(List<WaterTypeDemand> waterTypeDemandList) {
        this.waterTypeDemandList = waterTypeDemandList;
    }

    public void addWaterTypeDemand(WaterTypeDemand waterTypeDemand) {
        this.waterTypeDemandList.add(waterTypeDemand);
    }
}
