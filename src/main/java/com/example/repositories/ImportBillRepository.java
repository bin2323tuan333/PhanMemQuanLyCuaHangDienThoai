package com.example.repositories;

import com.example.models.Employee;
import com.example.models.ImportBill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportBillRepository {
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
  
  public ImportBill getImportBillByID(int id) {
    String sql = "SELECT * FROM ImportBill WHERE ";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
  
  public void insertImportBill(ImportBill i) {
    String sql = "INSERT INTO importbill (employee_id, import_date, supplier_id, total_amount) " +
                         "VALUES (?, ?, ?, ?);";
    DBHelper.Instance().executeUpd(sql,
            i.getEmployeeId(),
            i.getImportDate(),
            i.getSupplierId(),
            i.getTotalAmount());
  }
  
  public void updateImportBill(ImportBill i) {
    String sql = "UPDATE importbill  SET  employee_id = ?,  import_date = ?,  supplier_id = ?,  total_amount = ? " +
                         "WHERE import_id = ?;";
    DBHelper.Instance().executeUpd(sql,
            i.getEmployeeId(),
            i.getImportDate(),
            i.getSupplierId(),
            i.getTotalAmount(),
            i.getImportId());
  }
  
  public void deleteImportBill(int id) {
    String sql = "DELETE FROM importbill WHERE import_id = ?";
    DBHelper.Instance().executeUpd(sql, id);
  }
}
