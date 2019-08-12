package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    User findByEmail(String email) throws SQLException;

    void add(String IDCardNumber, String forename, String lastName, String email, String password, String country, String city, String zipCode, String address) throws SQLException;

    List<User> findAll() throws SQLException;
}
