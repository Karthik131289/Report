package com.wegot.venaqua.report.ws.response.tree;

import java.util.ArrayList;
import java.util.List;

public class BlockInfo {
    private String name;
    private List<HouseInfo> houses = new ArrayList<>();

    public BlockInfo() {
    }

    public BlockInfo(String name) {
        this.name = name;
    }

    public BlockInfo(String name, List<HouseInfo> houses) {
        this.name = name;
        this.houses = houses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HouseInfo> getHouses() {
        return houses;
    }

    public void setHouses(List<HouseInfo> houses) {
        this.houses = houses;
    }

    public void addHouse(HouseInfo house) {
        this.houses.add(house);
    }

    public void addHouse(List<HouseInfo> houses) {
        this.houses.addAll(houses);
    }

    public HouseInfo getHouse(String name) {
        for (HouseInfo house : houses) {
            if (house.getName().equals(name))
                return house;
        }
        return null;
    }
}
