package com.example.repositories;

import com.example.DTO.SystemSetting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemInfoRepository {
  public SystemSetting getSystemSetting() {
    String sql = "SELECT * FROM system_setting LIMIT 1";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
      if (rs.next()) {
        SystemSetting setting = new SystemSetting();
        setting.setId(rs.getInt("id"));
        setting.setShopName(rs.getString("shop_name"));
        setting.setShopAddress(rs.getString("shop_address"));
        setting.setShopPhone(rs.getString("shop_phone"));
        setting.setTaxCode(rs.getString("tax_code"));
        return setting;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public void updateSystemSetting(SystemSetting setting) {
    String sql = "UPDATE system_setting SET shop_name = ?, shop_address = ?, shop_phone = ?, tax_code = ? WHERE id = ?";
    try (Connection conn = DBHelper.Instance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, setting.getShopName());
      pstmt.setString(2, setting.getShopAddress());
      pstmt.setString(3, setting.getShopPhone());
      pstmt.setString(4, setting.getTaxCode());
      pstmt.setInt(5, setting.getId());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
