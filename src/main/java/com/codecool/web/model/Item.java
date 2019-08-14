package com.codecool.web.model;

public class Item extends AbstractModel {

    private String brand;
    private String NR;
    private String size;
    private Element element;
    private int amount;
    private String pic;
    private int length;
    private int price;
    private int flexIndex;


    public Item(int id, String brand, String NR, Element element, String size, int amount, String pic, int price) {
        super(id);
        this.brand = brand;
        this.NR = NR;
        this.element = element;
        this.size = size;
        this.amount = amount;
        this.pic = pic;
        this.price = price;
    }

    public Item(int id, String brand, String NR, Element element, int amount, String pic, int length, int price, int flexIndex) {
        super(id);
        this.brand = brand;
        this.NR = NR;
        this.element = element;
        this.amount = amount;
        this.pic = pic;
        this.length = length;
        this.price = price;
        this.flexIndex = flexIndex;
    }

    public String getBrand() {
        return brand;
    }

    public String getNR() {
        return NR;
    }

    public String getSize() {
        return size;
    }

    public Element getElement() {
        return element;
    }

    public int getAmount() {
        return amount;
    }

    public String getPic() {
        return pic;
    }

    public int getLength() {
        return length;
    }

    public int getPrice() {
        return price;
    }

    public int getFlexIndex() {
        return flexIndex;
    }
}
