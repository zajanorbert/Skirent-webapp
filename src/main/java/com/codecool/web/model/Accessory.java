package com.codecool.web.model;


public class Accessory extends Product {

    private int length;
    private int flexIndex;

    public Accessory(int id, String brand, String NR, Element element, int amount, String pic, int price, int length, int flexIndex) {
        super(id, brand, NR, element, amount, pic, price);
        this.length = length;
        this.flexIndex = flexIndex;
    }

    public int getLength() {
        return length;
    }

    public int getFlexIndex() {return flexIndex;}
}
