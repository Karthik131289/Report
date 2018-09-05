package com.wegot.venaqua.report.ws;

import javax.xml.bind.annotation.XmlElement;
import java.util.*;

public class SiteConsumptionByWaterSourceResponse {
    private List<WaterSource> waterSourceList = new ArrayList<WaterSource>();

    public SiteConsumptionByWaterSourceResponse() {

    }
    public SiteConsumptionByWaterSourceResponse(List<WaterSource> waterSourceList) {
        this.waterSourceList = waterSourceList;
    }

    @XmlElement(required = true)
    public List<WaterSource> getWaterSourceList() {
        return waterSourceList;
    }

    public void setWaterSourceList(List<WaterSource> waterSourceList) {
        this.waterSourceList = waterSourceList;
    }
}
