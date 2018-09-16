package com.example.levprovalov.firstapponmac;

import java.io.Serializable;

public class FilterData implements Serializable {
    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private boolean isChecked;
    private String name;

    public FilterData(String name) {
        this.isChecked = false;
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getName() {
        return name;
    }
}
