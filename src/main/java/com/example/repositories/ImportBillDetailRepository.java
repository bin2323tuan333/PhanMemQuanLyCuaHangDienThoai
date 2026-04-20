package com.example.repositories;

import com.example.models.ImportBill;
import com.example.models.ImportBillDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ImportBillDetailRepository {
    public void addImportBillDetail(int billId, int productId, int quantity, double unitPrice) {
        String sql = "INSERT INTO ImportBillDetails (import_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
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
    public void deleteImportBillDetailsByBillId(int billId) {
        String sql = "DELETE FROM ImportBillDetails WHERE import_id = ?";
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
        String sql = "UPDATE ImportBillDetails SET quantity = ?, unit_price = ? WHERE import_id = ? AND product_id = ?";
        try (Connection conn = DBHelper.Instance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, detail.getQuantity());
            stmt.setDouble(2, detail.getUnitPrice());
            stmt.setInt(3, detail.getImportId());
            stmt.setInt(4, detail.getProductId());
        } catch (Exception e) {
            e.printStackTrace();
        }}
        public void searchImportBillDetailsByBillId(int id ) {
            String sql = "SELECT * FROM ImportBillDetails WHERE import_id = ?";
            try (Connection conn = DBHelper.Instance().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (var rs = stmt.executeQuery()) {
                    while (rs != null && rs.next()) {
                        ImportBillDetail detail = new ImportBillDetail();
                        detail.setFromRS(rs);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


