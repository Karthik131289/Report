package com.wegot.venaqua.report.ws.response;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class PumpStateAndYieldResponse {
    private Date x;
    private Date x2;
    private int y;
    private String label;
    private long yieldValue;
    private int yieldPercentage;
    private double partialFill;

    public PumpStateAndYieldResponse() {
    }

    @XmlElement(required = true)
    public Date getX() {
        return x;
    }

    public void setX(Date x) {
        this.x = x;
    }

    @XmlElement(required = true)
    public Date getX2() {
        return x2;
    }

    public void setX2(Date x2) {
        this.x2 = x2;
    }

    @XmlElement(required = true)
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @XmlElement(required = true)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlElement(required = true)
    public long getYieldValue() {
        return yieldValue;
    }

    public void setYieldValue(long yieldValue) {
        this.yieldValue = yieldValue;
    }

    @XmlElement(required = true)
    public int getYieldPercentage() {
        return yieldPercentage;
    }

    public void setYieldPercentage(int yieldPercentage) {
        this.yieldPercentage = yieldPercentage;
    }

    @XmlElement(required = true)
    public double getPartialFill() {
        return partialFill;
    }

    public void setPartialFill(double partialFill) {
        this.partialFill = partialFill;
    }
}
