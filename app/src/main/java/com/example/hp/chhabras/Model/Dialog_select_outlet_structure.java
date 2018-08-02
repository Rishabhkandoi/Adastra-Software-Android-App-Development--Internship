package com.example.hp.chhabras.Model;

/**
 * Created by hp on 26-06-2018.
 */

public class Dialog_select_outlet_structure {

    private String areaName, areaAddress;

    public Dialog_select_outlet_structure(String areaName, String areaAddress) {
        this.areaName = areaName;
        this.areaAddress = areaAddress;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaAddress() {
        return areaAddress;
    }

    public void setAreaAddress(String areaAddress) {
        this.areaAddress = areaAddress;
    }
}
