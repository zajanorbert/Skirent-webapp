package com.codecool.web.dao.database;

import com.codecool.web.dao.WearDao;
import com.codecool.web.model.Element;
import com.codecool.web.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DatabaseWearDao extends AbstractDao implements WearDao {

    public DatabaseWearDao(Connection connection) {
        super(connection);
    }

    @Override
    public void addWear(String brand, String NR, Element element, int amount, int price, String pic, String size) throws SQLException {
        addProduct(brand, NR, element, amount, price, pic);
        String sql2 = "INSERT INTO arguments (size) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, size);
            executeInsert(statement);
        }
    }

    @Override
    public List<Product> findByElement(Element element) throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAllSled() throws SQLException {
        return null;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        return null;
    }

    @Override
    public Product addProduct(String brand, String NR, Element element, int amount, int price, String pic) {
        return null;
    }
}
