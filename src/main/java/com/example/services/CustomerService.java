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
  
  public List<CustomerInfo> searchCustomerInfos(String key) {
    CustomerRepository customerRepository = new CustomerRepository();
    if (key == null || key.trim().equals(""))
      return getAllCustomerInfos();
    return customerRepository.searchCustomerInfo(key);
  }
  
  public List<CustomerInfo> getAllCustomerInfos() {
    CustomerRepository customerRepository = new CustomerRepository();
    return customerRepository.getAllCustomerInfos();
  }
  
  public void updateCustomer(CustomerInfo cus) {
    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.updateCustomer(cus);
  }
  
  public void addCustomer(CustomerInfo cus) {
    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.insertCustomer(cus);
  }
  
  public void deleteCustomer(int customerId) {
    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.deleteCustomer(customerId);
  }
  
  public void searchCustomersByPhoneNumber(String phone) {
    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.searchCustomersByPhoneNumber(phone);
  }
  
  public void searchCustomersByName(String name) {
    CustomerRepository customerRepository = new CustomerRepository();
    customerRepository.searchCustomersByName(name);
  }
  
  public List<Customer> getAllCustomers() {
    CustomerRepository customerRepository = new CustomerRepository();
    return customerRepository.getAllCustomers();
  }
}
