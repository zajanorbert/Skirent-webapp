package com.codecool.web.service.simple;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.model.Element;
import com.codecool.web.model.Product;
import com.codecool.web.service.ProductService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleProductSevice implements ProductService {

    private final ProductDao productDao;

    public SimpleProductSevice(ProductDao productDao) {
        this.productDao = productDao;
    }

    /*@Override
    public void addSled(String brand, String NR, Element element, int amount, int price, String pic, String size) throws SQLException, ServiceException {
        try {
            productDao.addSled(brand, NR, element, amount, price, pic, size);
        }catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public void addAccessory(String brand, String NR, Element element, int amount, int price, String pic, int length, int flex) throws SQLException, ServiceException {        try {
            productDao.addAccessory(brand, NR, element, amount, price, pic, length, flex);
        }catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }*/

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
}
