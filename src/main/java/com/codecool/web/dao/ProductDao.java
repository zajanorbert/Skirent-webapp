package com.codecool.web.dao;

import com.codecool.web.model.Element;
import com.codecool.web.model.Item;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    Item findByElement(Element element) throws SQLException;

    List<Item> findAll() throws SQLException;

    void addSled(String brand, String NR, Element element, int amount, int price, String pic, String size) throws SQLException;

    void addAccessory(String brand, String NR, Element element, int amount, int price, String pic, int length, int flex) throws SQLException;
}
