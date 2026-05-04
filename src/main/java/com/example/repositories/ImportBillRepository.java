package com.example.repositories;

import com.example.DTO.ImportBillInfo;
import com.example.models.ImportBill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImportBillRepository {
  public List<ImportBillInfo> getAllImportBillInfos() {
    List<ImportBillInfo> list = new ArrayList<>();
    String query = "SELECT *\n" +
                           "FROM importbill ib \n" +
                           "JOIN employee e ON ib.employee_id = e.employee_id \n" +
                           "JOIN supplier s ON ib.supplier_id = s.supplier_id";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
      
      while (rs.next()) {
        ImportBillInfo info = new ImportBillInfo();
        info.setFromRS(rs);
        list.add(info);
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return list;
  }
  
  public List<ImportBill> getAllImportBills() {
    List<ImportBill> list = new ArrayList<>();
    String sql = "SELECT * FROM importbill";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          ImportBill e = new ImportBill();
          e.setFromRS(rs);
          list.add(e);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }
  
  public int addImportBill(ImportBill importBill) {
    String sql = "INSERT INTO importbill (supplier_id, employee_id, import_date, total_amount) VALUES (?, ?, NOW(), ?)";
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      
      pstmt.setInt(1, importBill.getSupplierId());
      pstmt.setInt(2, importBill.getEmployeeId());
      pstmt.setDouble(3, importBill.getTotalAmount());
      
      int affectedRows = pstmt.executeUpdate();
      
      if (affectedRows > 0) {
        try (ResultSet rs = pstmt.getGeneratedKeys()) {
          if (rs.next()) {
            int generatedId = rs.getInt(1);
            importBill.setImportId(generatedId);
            return generatedId;
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }
  
  public ImportBill getImportBillByID(int id) {
    String sql = "SELECT * FROM ImportBill WHERE import_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          ImportBill e = new ImportBill();
          e.setFromRS(rs);
          return e;
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return null;
  }
  
  public void insertImportBill(ImportBillInfo i) {
    String sql = "INSERT INTO ImportBill (employee_id, import_date, supplier_id, total_amount) " +
                         "VALUES (?, ?, ?, ?);";
    DBHelper.Instance().executeUpd(sql,
            i.getEmployee(),
            i.getImportDate(),
            i.getSupplier(),
            i.getTotalAmount());
  }
  
  public void updateImportBill(ImportBillInfo i) {
    String sql = "UPDATE ImportBill  SET  employee_id = ?,  import_date = ?,  supplier_id = ?,  total_amount = ? " +
                         "WHERE import_id = ?;";
    DBHelper.Instance().executeUpd(sql,
            i.getEmployee(),
            i.getImportDate(),
            i.getSupplier(),
            i.getTotalAmount(),
            i.getImportId());
  }
  
  public void deleteImportBill(int id) {
    String sql = "DELETE FROM ImportBill WHERE import_id = ?";
    DBHelper.Instance().executeUpd(sql, id);
  }
  
  public List<ImportBillInfo> searchImportBills(String key) {
    List<ImportBillInfo> list = new ArrayList<>();
    
    String query = """
                SELECT *
                FROM ImportBill ib
                LEFT JOIN employee e ON ib.employee_id = e.employee_id
                LEFT JOIN supplier s ON ib.supplier_id = s.supplier_id
                WHERE
                    LOWER(IFNULL(e.employee_name, '')) LIKE ?
                    OR LOWER(IFNULL(s.name, '')) LIKE ?
                    OR CAST(ib.import_id AS CHAR) LIKE ?
            """;
    
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
      
      String likeKey = "%" + key.toLowerCase().trim() + "%";
      
      ps.setString(1, likeKey);
      ps.setString(2, likeKey);
      ps.setString(3, likeKey);
      
      try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
          ImportBillInfo info = new ImportBillInfo();
          info.setFromRS(rs);
          list.add(info);
        }
      }
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return list;
  }
}
