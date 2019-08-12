package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.model.UserType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUserDao extends AbstractDao implements UserDao {
    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        if (email == null || "".equals(email)) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        String sql = "SELECT ID_card_number, forename, lastName, email, password, country, city, zip_code, address, user_type FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public void add(String IDCardNumber, String forename, String lastName, String email, String password, String country, String city, String zipCode, String address) throws SQLException {
        if (email == null || "".equals(email) || password == null)  {
            throw new IllegalArgumentException("Password or email cannot be null or empty");
        }
        String sql = "INSERT INTO users (ID_card_number, forename, lastName, email, password, country, city, zip_code, address, user_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'USER')";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, IDCardNumber);
            statement.setString(2, forename);
            statement.setString(3, lastName);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setString(6, country);
            statement.setString(7, city);
            statement.setString(8, zipCode);
            statement.setString(9, address);
            executeInsert(statement);
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    private User fetchUser(ResultSet resultSet) throws SQLException {
        String ID_card_number = resultSet.getString("ID_card_number");
        String forename = resultSet.getString("forename");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String country = resultSet.getString("country");
        String city = resultSet.getString("city");
        String zipCode = resultSet.getString("zip_code");
        String address = resultSet.getString("address");
        UserType userType = UserType.valueOf(resultSet.getString("user_type"));
        return new User(ID_card_number, forename, lastName, email, password, country, city, zipCode, address, userType);

    }
}
