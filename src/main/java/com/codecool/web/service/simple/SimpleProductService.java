package com.codecool.web.service.simple;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.model.Element;
import com.codecool.web.model.Product;
import com.codecool.web.service.ProductService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleProductService implements ProductService {

    private final ProductDao productDao;

    public SimpleProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return productDao.findAll();
    }

    @Override
    public List<Product> findByElement(Element element) throws SQLException, ServiceException {
        try {
            return productDao.findByElement(element);
        }catch (IllegalArgumentException ex){
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<Product> findAllSled() throws SQLException, ServiceException {
        try {
            return productDao.findAllSled();
        }catch (IllegalArgumentException ex){
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<Product> findAllSki() throws SQLException, ServiceException {
        try {
            return productDao.findAllSki();
        }catch (IllegalArgumentException ex){
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<Product> findAllSnowboard() throws SQLException, ServiceException {
        try {
            return productDao.findAllSnowboard();
        }catch (IllegalArgumentException ex){
            throw new ServiceException(ex.getMessage());
        }
    }
}
