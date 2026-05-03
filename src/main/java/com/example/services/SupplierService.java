package com.example.services;

import com.example.models.Supplier;
import com.example.repositories.SupplierRepository;

import java.util.List;

public class SupplierService {
  public List<Supplier> getAllSuppliers() {
    SupplierRepository supplierRepository = new SupplierRepository();
    return supplierRepository.getAllSuppliers();
  }
  
  public Supplier getSupplierByPhone(String phone) {
    SupplierRepository supplierRepository = new SupplierRepository();
    return supplierRepository.getSupplierByPhone(phone);
  }
  public void addSupplier(Supplier supplier) {
    SupplierRepository supplierRepository = new SupplierRepository();
    supplierRepository.insertSupplier(supplier);
  }
  public  void updateSupplier(Supplier supplier) {
    SupplierRepository supplierRepository = new SupplierRepository();
    supplierRepository.updateSupplier(supplier);
  }
    public void deleteSupplier(int id) {
      SupplierRepository supplierRepository = new SupplierRepository();
      supplierRepository.deleteSupplier(id);
    }
}
