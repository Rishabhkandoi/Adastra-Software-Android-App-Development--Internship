package com.example.hp.chhabras.Model;

/**
 * Created by hp on 27-06-2018.
 */

public class Gallery_structure_temp {

    private String text;
    private int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Gallery_structure_temp(int image, String text) {
        this.image = image;
        this.text = text;
    }
}
