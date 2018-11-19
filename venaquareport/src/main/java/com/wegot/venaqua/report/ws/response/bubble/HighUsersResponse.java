package com.wegot.venaqua.report.ws.response.bubble;

import java.util.ArrayList;
import java.util.List;

public class HighUsersResponse {
    private String name;
    private List<Children> children = new ArrayList<>();
    public HighUsersResponse() {
    }

    public HighUsersResponse(String name) {
        this.name = name;
    }

    public HighUsersResponse(String name, List<Children> children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Children> getChildren() {
        return children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public void addChildren(Children children) {
        this.children.add(children);
    }
}
