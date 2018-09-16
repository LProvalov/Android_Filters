package com.example.levprovalov.firstapponmac;

import java.util.ArrayList;

public class CustomersData {
    private String name;
    private ArrayList<Site> sites;

    public CustomersData(String name) {
        this.name = name;
        this.sites = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Site> getSites() {
        return sites;
    }
}

