package com.codecool.web.dao.database;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.model.Element;
import com.codecool.web.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseProductDao extends AbstractDao implements ProductDao {
    public DatabaseProductDao(Connection connection) { super(connection);}

    @Override
    public Item findByElement(Element element) throws SQLException{
        if(element == null){
            throw new IllegalArgumentException("Element field is empty");
        }
        String sql = "(SELECT item_id, brand, NR, element, amount, price, pic FROM items) UNION (SELECT item_id, size, length, flex_index FROM arguments) WHERE element = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setObject(1, element);
            try(ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()){
                    return fetchProduct(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public List<Item> findAll() throws SQLException{
        List<Item> storage = new ArrayList<>();
        String sqlStatement = "(SELECT * FROM items) union (SELECT * FROM arguments)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement)){
            try (ResultSet resultSet= preparedStatement.executeQuery()){
                while (resultSet.next()){
                    storage.add(fetchProduct(resultSet));
                }
            }
        }
        catch (SQLException e ){
            connection.rollback();
        }
        return storage;
    }

    @Override
    public void addSled(String brand, String NR, Element element, int amount, int price, String pic, String size) throws SQLException{
        String sql2 = "INSERT INTO arguments (size) VALUES (?)";
        String sql = "INSERT INTO items (brand, NR, element, amount, price, pic) VALUES (?,?,?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, brand);
            statement.setString(2, NR);
            statement.setObject(3, element);
            statement.setInt(4, amount);
            statement.setInt(5, price);
            statement.setString(6, pic);
            executeInsert(statement);
        }
        try(PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setString(1, size);
            executeInsert(statement);
        }
    }

    @Override
    public void addAccessory(String brand, String NR, Element element, int amount, int price, String pic, int length, int flex) throws SQLException{
        String sql2 = "INSERT INTO arguments (length, flex_index) VALUES (?, ?)";
        String sql = "INSERT INTO items (brand, NR, element, amount, price, pic) VALUES (?,?,?,?,?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, brand);
            statement.setString(2, NR);
            statement.setObject(3, element);
            statement.setInt(4, amount);
            statement.setInt(5, price);
            statement.setString(6, pic);
            executeInsert(statement);
        }
        try(PreparedStatement statement = connection.prepareStatement(sql2)) {
            statement.setInt(1, length);
            statement.setInt(1, flex);
            executeInsert(statement);
        }
    }



    private Item fetchProduct(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("item_id");
        String brand = resultSet.getString("brand");
        String NR = resultSet.getString("NR");
        Element element = Element.valueOf(resultSet.getString("element"));
        int amount = resultSet.getInt("amount");
        int price = resultSet.getInt("price");
        String pic = resultSet.getString("pic");
        String size = resultSet.getString("size");
        int length = resultSet.getInt("length");
        int flex = resultSet.getInt("flex_index");
        if(size != null){
            return new Item(id, brand, NR, element, size, amount, pic, price);
        }else {
            return new Item(id, brand, NR, element, amount, pic, length, price, flex);
        }
    }
}
