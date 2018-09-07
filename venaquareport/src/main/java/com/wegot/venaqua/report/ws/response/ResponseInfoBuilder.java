package com.wegot.venaqua.report.ws.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseInfoBuilder {
    private List<WaterSource> siteConsumptionByWaterSource = new ArrayList<>();

    public ResponseInfoBuilder withSiteConsumptionByWaterSource(List<WaterSource> waterSources) {
        this.siteConsumptionByWaterSource = waterSources;
        return this;
    }

    public ResponseInfo build() {
        return new ResponseInfo();
    }
}
