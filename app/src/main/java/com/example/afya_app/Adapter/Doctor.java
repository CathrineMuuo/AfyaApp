package com.example.afya_app.Adapter;

public class Doctor {
    private String name;
    private int imageResource;

    public Doctor(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
