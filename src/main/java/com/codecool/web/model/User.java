package com.codecool.web.model;

public class User {
    private String IDCardNumber;
    private String forename;
    private String lastName;
    private String email;
    private String password;
    private String country;
    private String city;
    private String zipCode;
    private String address;
    private UserType usertype;

    public User(String IDCardNumber, String forename, String lastName, String email, String password, String country, String city, String zipCode, String address,UserType usertype) {
        this.IDCardNumber = IDCardNumber;
        this.forename = forename;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
        this.usertype = usertype;
    }

    public User(String IDCardNumber, String email) {
        this.IDCardNumber = IDCardNumber;
        this.email = email;
        this.password = null;
    }

    public String getName(){
        return this.forename+" "+this.lastName;
    }

    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public String getForename() {
        return forename;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public UserType getUsertype() {
        return usertype;
    }
}
