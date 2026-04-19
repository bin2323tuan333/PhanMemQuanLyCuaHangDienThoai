package com.example.repositories;

import com.example.models.Customer;
import com.example.utils.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    // Lấy tất cả khách hàng
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer ORDER BY customer_id DESC";
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setBirthday(rs.getDate("birthday"));
                customer.setAddress(rs.getString("address"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    // Lấy khách hàng theo ID
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM Customer WHERE customer_id = ?";
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setBirthday(rs.getDate("birthday"));
                customer.setAddress(rs.getString("address"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // Tìm kiếm khách hàng theo tên
    public List<Customer> searchCustomersByName(String keyword) {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer WHERE full_name LIKE ? ORDER BY customer_id DESC";
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setBirthday(rs.getDate("birthday"));
                customer.setAddress(rs.getString("address"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customers;
    }

    // Thêm khách hàng mới
    public boolean addCustomer(Customer customer) {
        String sql = "INSERT INTO Customer (full_name, birthday, address, phone_number) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBHelper.Instance().getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, customer.getFullName());
            pstmt.setDate(2, customer.getBirthday() != null ? new java.sql.Date(customer.getBirthday().getTime()) : null);
            pstmt.setString(3, customer.getAddress());
            pstmt.setString(4, customer.getPhoneNumber());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customer.setCustomerId(generatedKeys.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Cập nhật thông tin khách hàng
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET full_name = ?, birthday = ?, address = ?, phone_number = ? WHERE customer_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBHelper.Instance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customer.getFullName());
            pstmt.setDate(2, customer.getBirthday() != null ? new java.sql.Date(customer.getBirthday().getTime()) : null);
            pstmt.setString(3, customer.getAddress());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.setInt(5, customer.getCustomerId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Xóa khách hàng
    public boolean deleteCustomer(int customerId) {
        String sql = "DELETE FROM Customer WHERE customer_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBHelper.Instance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, customerId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Đếm tổng số khách hàng
    public int getTotalCustomers() {
        String sql = "SELECT COUNT(*) as total FROM Customer";
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    // Kiểm tra số điện thoại đã tồn tại chưa
    public boolean isPhoneNumberExists(String phoneNumber) {
        String sql = "SELECT COUNT(*) as count FROM Customer WHERE phone_number = ?";
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phoneNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("count") > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Lấy khách hàng theo số điện thoại
    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM Customer WHERE phone_number = ?";
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DBHelper.Instance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phoneNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setBirthday(rs.getDate("birthday"));
                customer.setAddress(rs.getString("address"));
                customer.setPhoneNumber(rs.getString("phone_number"));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}