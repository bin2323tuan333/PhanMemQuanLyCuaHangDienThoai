package com.example.services;

import com.example.DTO.CustomerInfo;
import com.example.models.Customer;
import com.example.repositories.CustomerRepository;

import java.util.List;

public class CustomerService {
  public Customer getCustomerByPhoneNumber(String phone) {
    CustomerRepository customerRepository = new CustomerRepository();
    return customerRepository.getCustomerByPhoneNumber(phone);
  }
  
  public List<CustomerInfo> getAllCustomerInfos() {
    CustomerRepository customerRepository = new CustomerRepository();
    return customerRepository.getAllCustomerInfos();
  }
}
