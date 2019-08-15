package com.codecool.web.dao.database;

import com.codecool.web.dao.AccessoryDao;
import com.codecool.web.model.Element;
import com.codecool.web.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseAccesoryDao extends AbstractDao implements AccessoryDao {
    public DatabaseAccesoryDao(Connection connection) {
        super(connection);
    }

    @Override
    public void addAccessory(String brand, String NR, Element element, int amount, int price, String pic, int length, int flex) throws SQLException {
        Product product = addProduct(brand, NR, element, amount, price, pic);
        int id = product.getId();
        String sql2 = "INSERT INTO accessory (item_id, length, flex_index) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setInt(1, id);
            statement.setInt(2, length);
            statement.setInt(3, flex);
            executeInsert(statement);
        }
    }

    @Override
    public List<Product> findByElement(Element element) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return null;
    }

    @Override
    public Product addProduct(String brand, String NR, Element element, int amount, int price, String pic) throws SQLException {
        return null;
    }
}
