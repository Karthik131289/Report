package com.wegot.venaqua.report.ws.response;

import javax.xml.bind.annotation.XmlElement;

public class SiteUsageByWaterSource {
    private String name;
    private double usage;

    public SiteUsageByWaterSource() {
    }

    public SiteUsageByWaterSource(String name, double usage) {
        this.name = name;
        this.usage = usage;
    }

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(required = true)
    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }
}
