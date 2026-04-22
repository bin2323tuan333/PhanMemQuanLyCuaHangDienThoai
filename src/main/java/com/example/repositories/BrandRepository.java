package com.example.repositories;

import com.example.models.Bill;
import com.example.models.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandRepository {
  public List<Brand> getAllBrands() {
    List<Brand> list = new ArrayList<>();
    String sql = "SELECT * FROM Brand";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs != null && rs.next()) {
          Brand brand = new Brand();
          brand.setFromRS(rs);
          list.add(brand);
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return list;
  }
  
  public Brand getBrandById(int id) {
    String sql = "SELECT * FROM Brand WHERE brand_id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, id);
      try (ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
          Brand brand = new Brand();
          brand.setFromRS(rs);
          return brand;
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getErrorCode());
    }
    return null;
  }
  
  public void insertBrand(Brand b) {
    String sql = "INSERT INTO brand (brand_name) " +
                         "VALUES (?);";
    DBHelper.Instance().executeUpd(sql, b.getBrandName());
  }
  
  public void updateBrand(Brand b) {
    String sql = "UPDATE brand " +
                         "SET brand_name = ? " +
                         "WHERE brand_id = ?;";
    DBHelper.Instance().executeUpd(sql, b.getBrandName(), b.getBrandId());
  }
  
  public void deleteBrand(int id) {
    String sql = "DELETE FROM brand " +
                         "WHERE brand_id = ?;";
    DBHelper.Instance().executeUpd(sql, id);
  }
}
