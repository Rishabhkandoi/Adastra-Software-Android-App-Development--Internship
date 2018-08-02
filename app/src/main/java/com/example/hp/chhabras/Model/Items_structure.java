package com.example.hp.chhabras.Model;

/**
 * Created by hp on 28-06-2018.
 */

public class Items_structure {

    private String itemName, pricePerKg, price;

    public Items_structure(String itemName, String pricePerKg, String price) {
        this.itemName = itemName;
        this.pricePerKg = pricePerKg;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(String pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
