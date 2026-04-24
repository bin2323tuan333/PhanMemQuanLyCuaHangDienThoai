package com.example.services;

import com.example.models.Customer;
import com.example.repositories.CustomerRepository;

public class CustomerService {
  public Customer getCustomerByPhoneNumber(String phone) {
    CustomerRepository customerRepository = new CustomerRepository();
    return customerRepository.getCustomerByPhoneNumber(phone);
  }
}
