package com.example.repositories;

import com.example.models.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class SupplierRepository {
    public Supplier getSupplierByID(int id) {
        Supplier supplier = new Supplier();
        try {
            String sql = "SELECT * FROM Supplier WHERE supplier_id = ?";
           Connection conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
                var rs = pstmt.executeQuery();
            if (rs.next()) {
                supplier.setFromRS(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
}
        return supplier;
    }
    public Supplier getSupplierByName(String name) {
        Supplier supplier = new Supplier();
        try {
            String sql = "SELECT * FROM Supplier WHERE name = ?";
            Connection conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                supplier.setFromRS(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
}
        return supplier;
    }
    public void insertSupplier(Supplier supplier) {

        String sql = "INSERT INTO suppliers (supplier_id, name, address, phone, email) VALUES (?, ?, ?, ?, ?)";
        DBHelper.Instance().executeUpd(sql,
                supplier.getSupplierId(),
                supplier.getName(),
                supplier.getAddress(),
                supplier.getEmail(),

                supplier.getPhone());
    }
    public void updateSupplier(Supplier supplier) {
        String sql = "UPDATE Supplier SET name = ?, address = ?, phone = ?, email = ? WHERE supplier_id = ?";
        DBHelper.Instance().executeUpd(sql,
                supplier.getName(),
                supplier.getAddress(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getSupplierId());
    }
    public void deleteSupplier(int supplierId) {
        String sql = "DELETE FROM Supplier WHERE supplier_id = ?";
        DBHelper.Instance().executeUpd(sql, supplierId);
    }


    }

