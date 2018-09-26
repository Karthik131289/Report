package com.wegot.venaqua.report.ws.response;

import java.util.ArrayList;
import java.util.List;

public class HighUsers {
    private String name = "High Users";
    private List<HouseConsumption> houseList = new ArrayList<>();

    public HighUsers() {
    }

    public HighUsers(List<HouseConsumption> houseList) {
        this.houseList = houseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HouseConsumption> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<HouseConsumption> houseList) {
        this.houseList = houseList;
    }

    public void addHouse(String name, double usage) {
        HouseConsumption house = HouseConsumption.create(name, usage);
        this.houseList.add(house);
    }

    public void addHouse(HouseConsumption house) {
        this.houseList.add(house);
    }
}
