package com.wegot.venaqua.report.ws.response.gantt;

public class PumpStatus {
    private long onTime;
    private long offTime;
    private double yieldValue;
    private double yieldPercentage;
    private double partialFill;

    public PumpStatus() {
    }

    public long getOnTime() {
        return onTime;
    }

    public void setOnTime(long onTime) {
        this.onTime = onTime;
    }

    public long getOffTime() {
        return offTime;
    }

    public void setOffTime(long offTime) {
        this.offTime = offTime;
    }

    public double getYieldValue() {
        return yieldValue;
    }

    public void setYieldValue(double yieldValue) {
        this.yieldValue = yieldValue;
    }

    public double getYieldPercentage() {
        return yieldPercentage;
    }

    public void setYieldPercentage(double yieldPercentage) {
        this.yieldPercentage = yieldPercentage;
    }

    public double getPartialFill() {
        return partialFill;
    }

    public void setPartialFill(double partialFill) {
        this.partialFill = partialFill;
    }
}
