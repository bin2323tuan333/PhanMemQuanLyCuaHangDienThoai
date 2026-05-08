package com.example.services;

import com.example.models.Supplier;
import com.example.repositories.SupplierRepository;

import java.util.List;

public class SupplierService {
  public List<Supplier> getAllSuppliers() {
    SupplierRepository supplierRepository = new SupplierRepository();
    return supplierRepository.getAllSuppliers();
  }
  
  public List<Supplier> searchSuppliers(String keyword) {
    SupplierRepository repo = new SupplierRepository();
    
    if (keyword == null || keyword.trim().isEmpty()) {
      return repo.getAllSuppliers();
    }
    
    return repo.searchSuppliers(keyword);
  }
  
  public Supplier getSupplierByPhone(String phone) {
    SupplierRepository supplierRepository = new SupplierRepository();
    return supplierRepository.getSupplierByPhone(phone);
  }
  
  public void addSupplier(Supplier supplier) {
    SupplierRepository supplierRepository = new SupplierRepository();
    supplierRepository.insertSupplier(supplier);
  }
  
  public void updateSupplier(Supplier supplier) {
    SupplierRepository supplierRepository = new SupplierRepository();
    supplierRepository.updateSupplier(supplier);
  }
  
  public void deleteSupplier(int id) {
    SupplierRepository supplierRepository = new SupplierRepository();
    supplierRepository.deleteSupplier(id);
  }
  public boolean hasImportBill(int supplierId) {
    SupplierRepository supplierRepository = new SupplierRepository();
    return supplierRepository.hasImportBill(supplierId);
  }
}
