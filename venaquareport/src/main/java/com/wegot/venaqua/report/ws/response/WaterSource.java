package com.wegot.venaqua.report.ws.response;

import javax.xml.bind.annotation.XmlElement;

public class WaterSource {
    private String name;
    private double value;

    public WaterSource() {

    }

    public WaterSource(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(required = true)
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
