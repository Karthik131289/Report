package com.wegot.venaqua.report.ws.response.gantt;

import java.util.ArrayList;
import java.util.List;

public class PumpInfo {
    private String id;
    private String label;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
