package com.wegot.venaqua.report.ws.response.waterMap;

import java.util.ArrayList;
import java.util.List;

public class WaterMapResponse {
    private List<SiteDayUsage> siteDayUsageList = new ArrayList<>();

    public WaterMapResponse() {
    }

    public WaterMapResponse(List<SiteDayUsage> siteDayUsageList) {
        this.siteDayUsageList = siteDayUsageList;
    }

    public List<SiteDayUsage> getSiteDayUsageList() {
        return siteDayUsageList;
    }

    public void setSiteDayUsageList(List<SiteDayUsage> siteDayUsageList) {
        this.siteDayUsageList = siteDayUsageList;
    }

    public void addSiteDayUsage(SiteDayUsage siteDayUsage) {
        this.siteDayUsageList.add(siteDayUsage);
    }
}
