package com.wegot.venaqua.report.ws.response;

import java.util.ArrayList;
import java.util.List;

public class WaterSourceUsageResponse {

    private List<WaterSource> waterSourceList = new ArrayList<>();

    public WaterSourceUsageResponse() {
    }

    public WaterSourceUsageResponse(List<WaterSource> waterSourceList) {
        this.waterSourceList = waterSourceList;
    }

    public List<WaterSource> getWaterSourceList() {
        return waterSourceList;
    }

    public void setWaterSourceList(List<WaterSource> waterSourceList) {
        this.waterSourceList = waterSourceList;
    }

    public void addWaterSourceUsage(WaterSource waterSource) {
        this.waterSourceList.add(waterSource);
    }
}
