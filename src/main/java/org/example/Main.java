package org.example;

import com.example.models.*;
import com.example.repositories.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Main {
  // List lưu ID để xóa cuối cùng
  private static List<Integer> roleIds = new ArrayList<>();
  private static List<Integer> empIds = new ArrayList<>();
  private static List<Integer> catIds = new ArrayList<>();
  private static List<Integer> brandIds = new ArrayList<>();
  private static List<Integer> supIds = new ArrayList<>();
  private static List<Integer> prodIds = new ArrayList<>();
  private static List<Integer> cusIds = new ArrayList<>();
  private static List<Integer> billIds = new ArrayList<>();
  
  public static void main(String[] args) {
    System.out.println("========== HE THONG TEST REPOSITORY TOAN DIEN ==========");
    try {
      // 1. Tao cac bang cha (Khong phu thuoc)
      testIndependentTables();
      
      // 2. Tao cac bang phu thuoc cap 1 (Employee phu thuoc Role)
      testEmployeeAndAccount();
      
      // 3. Tao cac bang phu thuoc cap 2 (Product phu thuoc Category, Brand, Supplier)
      testProduct();
      
      // 4. Tao cac bang phu thuoc cap 3 (Bill phu thuoc Employee, Customer)
      testBills();
      
      System.out.println("\n>>> TEST CRUD THANH CONG.");
      
      // 5. Xoa tat ca theo thu tu an toan (Bảng con -> Bảng cha)
      cleanUpAllData();
      
    } catch (Exception e) {
      System.err.println("\n[LOI]: " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  private static void testIndependentTables() {
    System.out.println("\n[1] Testing: Role, Category, Brand, Supplier, Customer...");
    
    RoleRepository roleRepo = new RoleRepository();
    Role r = new Role();
    r.setRoleName("Role_" + System.currentTimeMillis());
    roleRepo.insertRole(r);
    roleIds.add(roleRepo.getAllRoles().get(roleRepo.getAllRoles().size() - 1).getRoleId());
    
    CategoryRepository catRepo = new CategoryRepository();
    Category cat = new Category();
    cat.setCategoryName("Cat_" + System.currentTimeMillis());
    catRepo.insertCategory(cat);
    catIds.add(catRepo.getAllCategories().get(catRepo.getAllCategories().size() - 1).getCategoryId());
    
    BrandRepository brandRepo = new BrandRepository();
    Brand b = new Brand();
    b.setBrandName("Brand_" + System.currentTimeMillis());
    brandRepo.insertBrand(b);
    brandIds.add(brandRepo.getAllBrands().get(brandRepo.getAllBrands().size() - 1).getBrandId());
    
    SupplierRepository supRepo = new SupplierRepository();
    Supplier s = new Supplier();
    s.setName("Sup_" + System.currentTimeMillis());
    supRepo.insertSupplier(s);
    supIds.add(supRepo.getAllSuppliers().get(supRepo.getAllSuppliers().size() - 1).getSupplierId());
    
    CustomerRepository cusRepo = new CustomerRepository();
    Customer cus = new Customer();
    cus.setFullName("Customer Test");
    cus.setPhoneNumber("0" + (long) (Math.random() * 1000000000));
    cusRepo.insertCustomer(cus);
    cusIds.add(cusRepo.getAllCustomers().get(0).getCustomerId());
  }
  
  private static void testEmployeeAndAccount() {
    System.out.println("[2] Testing: Employee & Account...");
    EmployeeRepository empRepo = new EmployeeRepository();
    Employee e = new Employee();
    e.setFullName("Staff Test");
    e.setSalary(15000000.0);
    e.setBirthday(Date.valueOf("1995-10-10"));
    empRepo.insertEmployee(e);
    int id = empRepo.getAllEmployees().get(empRepo.getAllEmployees().size() - 1).getEmployeeId();
    empIds.add(id);
    
    AccountRepository accRepo = new AccountRepository();
    Account acc = new Account();
    acc.setUsername("user_" + System.currentTimeMillis());
    acc.setPassword("123");
    acc.setEmployeeId(id);
    acc.setRoleId(roleIds.get(0));
    accRepo.insertAccount(acc);
    System.out.println("    - Created Account linked to Employee ID: " + id);
  }
  
  private static void testProduct() throws Exception {
    System.out.println("[3] Testing: Product...");
    ProductRepository repo = new ProductRepository();
    Product p = new Product();
    p.setProductName("Product Test " + System.currentTimeMillis());
    p.setPrice(20000000.0);
    p.setStock(10);
    p.setCategoryId(catIds.get(0));
    p.setBrandId(brandIds.get(0));
    
    repo.insertProduct(p);
    prodIds.add(repo.getAllProducts().get(repo.getAllProducts().size() - 1).getProductId());
  }
  
  private static void testBills() {
    System.out.println("[4] Testing: Bill...");
    BillRepository billRepo = new BillRepository();
    Bill b = new Bill();
    b.setTotalAmount(5000000.0);
    b.setInvoiceDate(new Timestamp(System.currentTimeMillis()));
    // GAN ID HOP LE TU CAC BANG DA TAO
    b.setEmployeeId(empIds.get(0));
    b.setCustomerId(cusIds.get(0));
    
    billRepo.insertBill(b);
    billIds.add(billRepo.getAllBills().get(0).getBillId());
    System.out.println("    - Created Bill linked to Employee " + empIds.get(0));
  }
  
  private static void cleanUpAllData() {
    System.out.println("\n--- Dọn dẹp dữ liệu theo thứ tự ngược ---");
    
    // 1. Xóa Account trước (con của Employee)
    AccountRepository accRepo = new AccountRepository();
    for (Account a : accRepo.getAllAccount()) {
      if (a.getUsername().contains("user_")) accRepo.deleteAccount(a.getAccountId());
    }
    
    // 2. Xóa Bill (con của Employee/Customer)
    BillRepository billRepo = new BillRepository();
    for (int id : billIds) billRepo.deleteBill(id);
    
    // 3. Xóa Product (con của Category/Brand/Supplier)
    ProductRepository prodRepo = new ProductRepository();
    for (int id : prodIds) prodRepo.deleteProduct(id);
    
    // 4. Xóa Employee & Customer
    EmployeeRepository empRepo = new EmployeeRepository();
    for (int id : empIds) empRepo.deleteEmployee(id);
    
    CustomerRepository cusRepo = new CustomerRepository();
    for (int id : cusIds) cusRepo.deleteCustomer(id);
    
    // 5. Xóa các bảng gốc cuối cùng
    SupplierRepository supRepo = new SupplierRepository();
    for (int id : supIds) supRepo.deleteSupplier(id);
    
    BrandRepository brandRepo = new BrandRepository();
    for (int id : brandIds) brandRepo.deleteBrand(id);
    
    CategoryRepository catRepo = new CategoryRepository();
    for (int id : catIds) catRepo.deteleCategory(id);
    
    RoleRepository roleRepo = new RoleRepository();
    for (int id : roleIds) roleRepo.deleteRole(id);
    
    System.out.println(">>> Cleanup OK.");
  }
}