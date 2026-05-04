package com.example.repositories;

import com.example.models.ImportBill;
import com.example.models.ImportBillDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImportBillDetailRepository {
  public void insertImportBillDetail(int importId, int productId, int quantity, double unitPrice) {
    String sql = "INSERT INTO ImportBillDetail (import_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, importId);
      stmt.setInt(2, productId);
      stmt.setInt(3, quantity);
      stmt.setDouble(4, unitPrice);
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void deleteImportBillDetail(int importDetailId) {
    String sql = "DELETE FROM ImportBillDetail WHERE import_detail_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, importDetailId);
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void deleteImportBillDetailsByImportId(int importId) {
    String sql = "DELETE FROM ImportBillDetail WHERE import_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, importId);
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void updateImportBillDetail(ImportBillDetail detail) {
    String sql = "UPDATE ImportBillDetail SET quantity = ?, unit_price = ? WHERE import_detail_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, detail.getQuantity());
      stmt.setDouble(2, detail.getUnitPrice());
      stmt.setInt(3, detail.getImportDetailId());
      stmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public List<ImportBillDetail> getImportBillDetailsByBillId(int importId) {
    List<ImportBillDetail> list = new ArrayList<>();
    String sql = "SELECT * FROM ImportBillDetail WHERE import_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, importId);
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


