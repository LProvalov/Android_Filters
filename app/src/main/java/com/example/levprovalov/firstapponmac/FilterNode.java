package com.example.levprovalov.firstapponmac;

import java.io.Serializable;
import java.util.ArrayList;

public class FilterNode<T> implements Serializable {

    private ArrayList<Integer> children;
    private int parent;
    private T data;

    public FilterNode(T data) {
        this.children = new ArrayList<>();
        this.parent = -1;
        this.data = data;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public ArrayList<Integer> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
