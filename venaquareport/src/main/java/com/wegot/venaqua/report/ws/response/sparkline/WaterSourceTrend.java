package com.wegot.venaqua.report.ws.response.sparkline;

import java.util.ArrayList;
import java.util.List;

public class WaterSourceTrend {
    private String waterSource;
    private List<Double> weeklyTrend = new ArrayList<>();
    private int performance;
    private List<Double> finalWeekTrend = new ArrayList<>();

    public WaterSourceTrend() {
    }

    public WaterSourceTrend(String waterSource, List<Double> weeklyTrend, int performance, List<Double> finalWeekTrend) {
        this.waterSource = waterSource;
        this.weeklyTrend = weeklyTrend;
        this.performance = performance;
        this.finalWeekTrend = finalWeekTrend;
    }

    public String getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }

    public List<Double> getWeeklyTrend() {
        return weeklyTrend;
    }

    public void setWeeklyTrend(List<Double> weeklyTrend) {
        this.weeklyTrend = weeklyTrend;
    }

    public void addWeeklyTrend(Double value) {
        this.weeklyTrend.add(value);
    }

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public List<Double> getFinalWeekTrend() {
        return finalWeekTrend;
    }

    public void setFinalWeekTrend(List<Double> finalWeekTrend) {
        this.finalWeekTrend = finalWeekTrend;
    }

    public void addFinalWeekTrend(Double value) {
        this.finalWeekTrend.add(value);
    }
}
