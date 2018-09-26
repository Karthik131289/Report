package com.wegot.venaqua.report.ws.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "House")
public class HouseConsumption {

    private String name;
    private double usage;

    public HouseConsumption() {
    }

    public HouseConsumption(String name, double usage) {
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

    public static HouseConsumption create(String name, double usage) {
        return new HouseConsumption(name, usage);
    }
}
