package com.example.repositories;

import com.example.DTO.BillDetailInfo;
import com.example.DTO.ImportBillDetailInfo;
import com.example.models.BillDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDetailRepository {
  public List<BillDetailInfo> getAllBillDetailInfos() {
    String sql = "SELECT * " +
                         "FROM BillDetail bd " +
                         "LEFT JOIN Product p ON bd.product_id = p.product_id " +
                         "LEFT JOIN Brand b ON p.brand_id = b.brand_id " +
                         "LEFT JOIN Category c ON p.category_id = c.category_id " +
                         "LEFT JOIN Supplier s ON p.supplier_id = s.supplier_id " +
                         "LIMIT 0, 1000";
    List<BillDetailInfo> list = new ArrayList<>();
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
      while (rs != null && rs.next()) {
        BillDetailInfo billDetail = new BillDetailInfo();
        billDetail.setFromRS(rs);
        list.add(billDetail);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public List<BillDetailInfo> getBillDetailInfosByBillId(int billId) {
    String sql = "SELECT * FROM billdetail bd \n" +
                         "LEFT JOIN product p ON bd.product_id = p.product_id \n" +
                         "LEFT JOIN brand b ON p.brand_id = b.brand_id \n" +
                         "LEFT JOIN category c ON p.category_id = c.category_id \n" +
                         "WHERE bd.bill_id = ?";
    
    List<BillDetailInfo> list = new ArrayList<>();
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, billId);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          BillDetailInfo billDetail = new BillDetailInfo();
          billDetail.setFromRS(rs);
          list.add(billDetail);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public List<ImportBillDetailInfo> getImportBillDetailInfosByBillId(int billId) {
    String sql = "SELECT * FROM importbilldetail ibd \n" +
                         "LEFT JOIN product p ON ibd.product_id = p.product_id \n" +
                         "LEFT JOIN brand b ON p.brand_id = b.brand_id\n" +
                         "LEFT JOIN category c ON p.category_id = c.category_id\n" +
                         "WHERE ibd.import_id = ?";
    
    List<ImportBillDetailInfo> list = new ArrayList<>();
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, billId);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          ImportBillDetailInfo billDetail = new ImportBillDetailInfo();
          billDetail.setFromRS(rs);
          list.add(billDetail);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public List<BillDetail> getAllBillDetails() {
    List<BillDetail> list = new ArrayList<>();
    String sql = "SELECT * FROM BillDetail";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          BillDetail billDetail = new BillDetail();
          billDetail.setFromRS(rs);
          list.add(billDetail);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return list;
  }
  
  public List<BillDetail> getBillDetailsByBillID(int billID) {
    List<BillDetail> list = new ArrayList<>();
    String sql = "SELECT * FROM BillDetail WHERE bill_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, billID);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          BillDetail billDetail = new BillDetail();
          billDetail.setFromRS(rs);
          list.add(billDetail);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return list;
  }
  
  public BillDetail getBillDetailByID(int BillDetailID) {
    String sql = "SELECT * FROM billdetail WHERE bill_detail_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, BillDetailID);
      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs != null && rs.next()) {
          BillDetail billDetail = new BillDetail();
          billDetail.setFromRS(rs);
          return billDetail;
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return null;
  }
  
  public void insertBillDetail(BillDetail bd) {
    String sql = "INSERT INTO billdetail (bill_id, product_id, quantity, unit_price) " +
                         "VALUES (?, ?, ?, ?);";
    DBHelper.Instance().executeUpd(sql,
            bd.getBillId(),
            bd.getProductId(),
            bd.getQuantity(),
            bd.getUnitPrice());
  }
  
  public void updateBillDetail(BillDetail bd) {
    String sql = "UPDATE billdetail  SET  bill_id = ?,  product_id = ?,  quantity = ?,  unit_price = ? " +
                         "WHERE bill_detail_id = ?;";
    DBHelper.Instance().executeUpd(sql,
            bd.getBillId(),
            bd.getProductId(),
            bd.getQuantity(),
            bd.getUnitPrice(),
            bd.getbillDetailId());
  }
  
  public void deleteBillDetail(int id) {
    String sql = "DELETE FROM billdetail " +
                         "WHERE bill_detail_id = ?;";
    DBHelper.Instance().executeUpd(sql, id);
  }
  
  public void insertBillDetail(int billId, int productId, int quantity, double price) {
    String sql = "INSERT INTO billdetail (bill_id, product_id, quantity, unit_price) " +
                         "VALUES (?, ?, ?, ?);";
    DBHelper.Instance().executeUpd(sql, billId, productId, quantity, price);
  }
}
