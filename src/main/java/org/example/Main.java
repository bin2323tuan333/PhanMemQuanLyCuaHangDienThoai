package org.example;

import com.example.models.Customer;
import com.example.utils.Database;

import java.util.Date;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Customer test = new Customer(1, "Tuan", new Date() , "Hue", "0999999999");
        System.out.println(test.toString());
        Database.getConnection();
    }
}