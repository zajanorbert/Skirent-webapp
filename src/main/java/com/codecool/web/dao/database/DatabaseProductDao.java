package com.codecool.web.dao.database;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.model.Element;
import com.codecool.web.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseProductDao extends AbstractDao implements ProductDao {
    public DatabaseProductDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Product> findByElement(Element element) throws SQLException {
        List<Product> storage = new ArrayList<>();
        if (element == null) {
            throw new IllegalArgumentException("Element field is empty");
        }
        String sql = "SELECT item_id, brand, NR, element, amount, price, pic FROM items WHERE element = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, element);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    storage.add(fetchProduct(resultSet));
                }
                return storage;
            }

        }

    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> storage = new ArrayList<>();
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sqlStatement = "SELECT * FROM storage";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    storage.add(fetchProduct(resultSet));
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(autoCommit);
        }
        return storage;
    }

    @Override
    public Product addProduct(String brand, String NR, Element element, int amount, int price, String pic) throws SQLException{
        String sql = "INSERT INTO storage (brand, NR, element, amount, price, pic) VALUES (?,?,?,?,?,?)";
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, brand);
            statement.setString(2, NR);
            statement.setObject(3, element);
            statement.setInt(4, amount);
            statement.setInt(5, price);
            statement.setString(6, pic);
            executeInsert(statement);
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return fetchProduct(resultSet);
            }
        }catch (SQLException e) {
            connection.rollback();
            throw e;
        }finally {
            connection.setAutoCommit(autoCommit);
        }
        throw new SQLException("Insert failed");
    }

    private Product fetchProduct(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("item_id");
        String brand = resultSet.getString("brand");
        String NR = resultSet.getString("NR");
        Element element = Element.valueOf(resultSet.getString("element"));
        int amount = resultSet.getInt("amount");
        int price = resultSet.getInt("price");
        String pic = resultSet.getString("pic");
/*        String size = resultSet.getString("size");
        int length = resultSet.getInt("length");
        int flex = resultSet.getInt("flex_index");*/
        return new Product(id, brand, NR, element, amount, pic, price);
    }
}
