package com.example.services;

import com.example.DTO.RecentBill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TempBillService implements IBillService {

    public List<RecentBill> getRecentBills(int limit) {
        List<RecentBill> list = new ArrayList<>();
        String[] customers = {"Alexander Wright", "Sophia Chen", "Jordan Henderson", "Lucia Garcia", "Minh Tuấn", "Thùy Chi", "John Wick", "Ellen Joe"};
        String[] employees = {"Nguyễn Văn A", "Trần Thị B", "Lê Văn C", "Phạm Thị D", "Hoàng Văn E", "Ngô Thị F", "Đặng Văn G", "Bùi Thị H"};
        String[] statuses = {"COMPLETED", "PROCESSING", "PENDING", "CANCELLED"};
        Random random = new Random();

        for (int i = 1; i <= limit; i++) {
            int id = 9000 + i;
            String customer = customers[random.nextInt(customers.length)];
            String employee = employees[random.nextInt(employees.length)];
            String status = statuses[random.nextInt(statuses.length)];
            double total = 1000000 + (19000000 * random.nextDouble());
            long randomTime = System.currentTimeMillis() - (long) random.nextInt(30) * 24 * 60 * 60 * 1000;
            Date date = new Date(randomTime);  // Trả về Date trực tiếp

            // Tạo RecentBill với Date
            RecentBill bill = new RecentBill(id, customer, date, total, employee, status);
            list.add(bill);
        }

        list.sort((b1, b2) -> Integer.compare(b2.getBillId(), b1.getBillId()));
        return list;
    }
}