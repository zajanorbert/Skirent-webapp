package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public class SimpleUserService implements UserService {
    
    private final UserDao userDao;
    
    public SimpleUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loginUser(String email, String password) throws SQLException, ServiceException {
        try {
            User user = userDao.findByEmail(email);
            if (user == null || !user.getPassword().equals(password)) {
                throw new ServiceException("Bad login");
            }
            return user;
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public void addUser(String IDCardNumber, String forename, String lastName, String email, String password, String country, String city, String zipCode, String address) throws SQLException, ServiceException {
        try {
            userDao.add(IDCardNumber, forename, lastName, email, password, country, city, zipCode, address);
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public boolean emailVerify(String email) throws SQLException, ServiceException {
        try {
            if (userDao.findByEmail(email) != null) {
                return true;
            } else {
                return false;
            }
        }catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
