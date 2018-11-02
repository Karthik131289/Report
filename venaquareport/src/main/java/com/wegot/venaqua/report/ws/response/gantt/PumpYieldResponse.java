package com.wegot.venaqua.report.ws.response.gantt;

import java.util.ArrayList;
import java.util.List;

public class PumpYieldResponse {
    private List<PumpInfo> pumpInfos = new ArrayList<>();

    public PumpYieldResponse() {
    }

    public PumpYieldResponse(List<PumpInfo> pumpInfos) {
        this.pumpInfos = pumpInfos;
    }

    public List<PumpInfo> getPumpInfos() {
        return pumpInfos;
    }

    public void setPumpInfos(List<PumpInfo> pumpInfos) {
        this.pumpInfos = pumpInfos;
    }

    public void addPumpInfo(PumpInfo pumpInfo) {
        this.pumpInfos.add(pumpInfo);
    }
}
