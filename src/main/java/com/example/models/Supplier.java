package com.example.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Supplier {
    private int supplierId;
    private String name;
    private String address;
    private String phone;
    private String email;
    public void setFromRS(ResultSet rs) throws SQLException {
        this.supplierId = rs.getInt("supplier_id");
        this.name = rs.getString("name");
        this.address = rs.getString("address");
        this.phone = rs.getString("phone");
        this.email = rs.getString("email");
    }

    public Supplier() {}
    public Supplier(int supplierId, String name, String address, String phone, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Supplier(id = " + this.supplierId + ", name = " + this.name + ", address = " + this.address + ", phone = " + this.phone + ", email = " + this.email + ")";
    }
}
