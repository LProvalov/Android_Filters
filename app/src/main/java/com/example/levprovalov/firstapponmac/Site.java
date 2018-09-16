package com.example.levprovalov.firstapponmac;

import java.util.ArrayList;

public class Site {
    private String name;
    private ArrayList<Buildnig> buildings;

    public Site(String name) {
        this.name = name;
        this.buildings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Buildnig> getBuildings() {
        return buildings;
    }
}
