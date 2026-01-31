package com.example.models;

import java.util.Date;

public class Customer {
    private int id;
    private String fullName;
    private Date birthday;
    private String address;
    private String phoneNumber;

    public Customer() {}
    public Customer(int id, String fullName, Date birthday, String address, String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return "Customer(id = " + this.id + ", fullName = " + this.fullName + ", birthday = " + (this.birthday).getDate() + "/" + ((this.birthday).getMonth() + 1) + "/" + ((this.birthday).getYear() + 1900)  + ", address = " + this.address + ", phoneNumber = " + this.phoneNumber + ")";
    }

}
