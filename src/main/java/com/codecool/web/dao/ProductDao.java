package com.codecool.web.dao;

import com.codecool.web.model.Element;
import com.codecool.web.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    List<Product> findByElement(Element element) throws SQLException;

    List<Product> findAllSled() throws SQLException;

    List<Product> findAllSki() throws SQLException;

    List<Product> findAllSnowboard() throws SQLException;

    List<Product> findAll() throws SQLException;

    Product addProduct(String brand, String NR, Element element, int amount, int price, String pic) throws SQLException;
}
