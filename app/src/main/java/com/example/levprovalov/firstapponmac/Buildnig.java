package com.example.levprovalov.firstapponmac;

import java.util.ArrayList;

public class Buildnig {
    private String name;
    private ArrayList<Inspection> inspections;

    public Buildnig(String name) {
        this.name = name;
        this.inspections = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Inspection> getInspections() {
        return inspections;
    }
}
