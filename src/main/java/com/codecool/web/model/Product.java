package com.codecool.web.model;

public class Product extends AbstractModel {

    private String brand;
    private String NR;
    private Element element;
    private int amount;
    private String pic;
    private int price;


    public Product(int id, String brand, String NR, Element element, int amount, String pic, int price) {
        super(id);
        this.brand = brand;
        this.NR = NR;
        this.element = element;
        this.amount = amount;
        this.pic = pic;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getNR() {
        return NR;
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

    public int getPrice() {
        return price;
    }

}
