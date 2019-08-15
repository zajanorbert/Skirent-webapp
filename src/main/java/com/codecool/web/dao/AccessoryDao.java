package com.codecool.web.dao;

import com.codecool.web.model.Element;

import java.sql.SQLException;

public interface AccessoryDao extends ProductDao {

    void addAccessory(String brand, String NR, Element element, int amount, int price, String pic, int length, int flex) throws SQLException;

}
