package com.example.afya_app;

public class ListData {

    String name;
    int loca,desc,price;
    int image;

    public String getName() {
        return name;
    }

    public int getLoca() {
        return loca;
    }

    public int getDesc() {
        return desc;
    }

    public int getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public ListData(String name, int loca, int desc, int price, int image) {
        this.name = name;
        this.loca = loca;
        this.desc = desc;
        this.price = price;
        this.image = image;
    }
}
