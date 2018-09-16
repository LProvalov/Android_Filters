package com.example.levprovalov.firstapponmac;

import java.io.Serializable;
import java.util.ArrayList;

public class FilterTree<T> implements Serializable {

    public final int ROOT_COUNT = 5;

    private ArrayList<FilterNode<T>> timeRoots;

    private ArrayList<FilterNode<T>> customers;
    private ArrayList<FilterNode<T>> sites;
    private ArrayList<FilterNode<T>> buildings;

    public FilterTree() {
        this.timeRoots = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.sites = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    public ArrayList<FilterNode<T>> getCustomers() {
        return customers;
    }

    public ArrayList<FilterNode<T>> getSites() {
        return sites;
    }

    public ArrayList<FilterNode<T>> getBuildings() {
        return buildings;
    }

    public ArrayList<FilterNode<T>> getTimeRoots() {
        return timeRoots;
    }

}
