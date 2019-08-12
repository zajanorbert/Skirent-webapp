package com.codecool.web.dao.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class AbstractDao {

    final Connection connection;

    AbstractDao(Connection connection) {
        this.connection = connection;
    }

    void executeInsert(PreparedStatement statement) throws SQLException {
        int insertCount = statement.executeUpdate();
        if (insertCount != 1) {
            connection.rollback();
            throw new SQLException("Expected 1 row to be inserted");
        }
    }

    int fetchGeneratedId(PreparedStatement statement) throws SQLException {
        int id;
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            } else {
                connection.rollback();

                throw new SQLException("Expected 1 result");
            }
        }
        connection.commit();
        return id;
    }
    public boolean checkString(String string,int min,int max) throws IllegalArgumentException{
        try {
            return (string.length() < min || string.length() > max);
        }catch (IllegalArgumentException ex){
            throw ex;
        }

    }

    public boolean checkInt(int value,int min,int max) throws IllegalArgumentException {
        try {
            return (value >= min || value <= max);
        }catch (IllegalArgumentException ex){
            throw ex;
        }
    }

}
