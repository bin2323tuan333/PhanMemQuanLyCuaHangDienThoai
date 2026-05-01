package com.example.services;

import com.example.models.Supplier;
import com.example.repositories.SupplierRepository;

import java.util.List;

public class SupplierService {
  public List<Supplier> getAllSuppliers() {
    SupplierRepository supplierRepository = new SupplierRepository();
    return supplierRepository.getAllSuppliers();
  }
}
