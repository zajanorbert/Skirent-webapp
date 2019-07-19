package com.codecool.web.model;

public class Buyers {
    private String IDCardNumber;
    private String forename;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private String postalCode;
    private String address;

    public Buyers(String IDCardNumber, String forename, String lastName, String email, String password, String city, String postalCode, String address) {
        this.IDCardNumber = IDCardNumber;
        this.forename = forename;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.postalCode = postalCode;
        this.address = address;
    }

    public Buyers(String IDCardNumber, String email) {
        this.IDCardNumber = IDCardNumber;
        this.email = email;
        this.password = null;
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

    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }
}
