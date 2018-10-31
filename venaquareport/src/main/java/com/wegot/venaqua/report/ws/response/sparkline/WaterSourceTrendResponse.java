package com.wegot.venaqua.report.ws.response.sparkline;

import java.util.ArrayList;
import java.util.List;

public class WaterSourceTrendResponse {
    private List<WaterSourceTrend> waterSources = new ArrayList<>();

    public WaterSourceTrendResponse() {
    }

    public WaterSourceTrendResponse(List<WaterSourceTrend> waterSources) {
        this.waterSources = waterSources;
    }

    public List<WaterSourceTrend> getWaterSources() {
        return waterSources;
    }

    public void setWaterSources(List<WaterSourceTrend> waterSources) {
        this.waterSources = waterSources;
    }

    public void addWaterSource(WaterSourceTrend waterSource) {
        this.waterSources.add(waterSource);
    }
}
