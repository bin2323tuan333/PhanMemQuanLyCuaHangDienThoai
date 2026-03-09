package com.example.models;

import java.util.Date;

public class Employee {
    private int employeeId;
    private String fullName;
    private String gender;
    private Date birthday;
    private String address;
    private String phoneNumber;
    private double salary;
    private String status;

    private int accountId; // FK -> Account

    public Employee() {}

    public Employee(int employeeId, String fullName, String gender, Date birthday,
                    String address, String phoneNumber, double salary, String status, int accountId) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.status = status;
        this.accountId = accountId;
    }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    @Override
    public String toString() {
        return fullName + " (" + phoneNumber + ")";
    }
}