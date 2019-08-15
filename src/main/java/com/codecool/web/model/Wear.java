package com.codecool.web.model;

public class Wear extends Product {

    private String size;

    public Wear(int id, String brand, String NR, Element element, String size, int amount, String pic, int price) {
        super(id, brand, NR, element, amount, pic, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
