package com.example.repositories;

import com.example.models.Bill;
import com.example.models.BillDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDetailRepository {
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
  
}
