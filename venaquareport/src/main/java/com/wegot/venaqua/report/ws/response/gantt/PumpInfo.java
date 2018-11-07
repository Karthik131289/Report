package com.wegot.venaqua.report.ws.response.gantt;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class PumpInfo {
    private String id;
    private String pumpId;
    private String label;
    @JsonIgnore( value = false)
    private double totalUsage;
    private List<PumpStatus> pumpStatusList = new ArrayList<>();

    public PumpInfo() {
    }

    public PumpInfo(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public PumpInfo(String id, String label, List<PumpStatus> pumpStatusList) {
        this.id = id;
        this.label = label;
        this.pumpStatusList = pumpStatusList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPumpId() {
        return pumpId;
    }

    public void setPumpId(String pumpId) {
        this.pumpId = pumpId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(double totalUsage) {
        this.totalUsage = totalUsage;
    }

    public List<PumpStatus> getPumpStatusList() {
        return pumpStatusList;
    }

    public void setPumpStatusList(List<PumpStatus> pumpStatusList) {
        this.pumpStatusList = pumpStatusList;
    }

    public void addPumpStatus(PumpStatus status) {
        this.pumpStatusList.add(status);
    }
}
