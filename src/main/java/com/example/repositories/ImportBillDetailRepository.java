package com.example.repositories;

import com.example.models.ImportBill;
import com.example.models.ImportBillDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImportBillDetailRepository {
  public void insertImportBillDetail(int billId, int productId, int quantity, double unitPrice) {
    String sql = "INSERT INTO ImportBillDetail (import_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, billId);
      stmt.setInt(2, productId);
      stmt.setInt(3, quantity);
      stmt.setDouble(4, unitPrice);
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void deleteImportBillDetail(int billId) {
    String sql = "DELETE FROM ImportBillDetail WHERE import_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, billId);
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void updateImportBillDetail(ImportBillDetail detail) {
    int billId = detail.getImportId();
    String sql = "UPDATE ImportBillDetail SET quantity = ?, unit_price = ? WHERE import_id = ? AND product_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, detail.getQuantity());
      stmt.setDouble(2, detail.getUnitPrice());
      stmt.setInt(3, detail.getImportId());
      stmt.setInt(4, detail.getProductId());
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public List<ImportBillDetail> getImportBillDetailsByBillId(int id) {
    List<ImportBillDetail> list = new ArrayList<>();
    String sql = "SELECT * FROM ImportBillDetail WHERE import_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, id);
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs != null && rs.next()) {
          ImportBillDetail detail = new ImportBillDetail();
          detail.setFromRS(rs);
          list.add(detail);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }
}


