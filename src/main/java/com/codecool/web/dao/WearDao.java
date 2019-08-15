package com.codecool.web.dao;

import com.codecool.web.model.Element;

import java.sql.SQLException;

public interface WearDao extends ProductDao {

    void addWear(String brand, String NR, Element element, int amount, int price, String pic, String size) throws SQLException;

}
