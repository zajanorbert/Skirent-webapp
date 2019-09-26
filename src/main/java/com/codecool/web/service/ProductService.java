package com.codecool.web.service;

import com.codecool.web.model.Element;
import com.codecool.web.model.Product;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<Product> findByElement(Element element) throws SQLException, ServiceException;

    List<Product> findAll() throws SQLException, ServiceException;

    List<Product> findAllSki() throws SQLException, ServiceException;

    List<Product> findAllSled() throws SQLException, ServiceException;

    List<Product> findAllSnowboard() throws SQLException, ServiceException;

/*
    void addSled(String brand, String NR, Element element, int amount, int price, String pic, String size) throws SQLException, ServiceException;

    void addAccessory(String brand, String NR, Element element, int amount, int price, String pic, int length, int flex) throws SQLException, ServiceException;
*/
}
