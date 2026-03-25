package com.example.services;

import com.example.DTO.RecentBill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TempBillService implements IBillService{
    public List<RecentBill> getRecentBills(int limit) {
        List<RecentBill> list = new ArrayList<>();
        String[] customers = {"Alexander Wright", "Sophia Chen", "Jordan Henderson", "Lucia Garcia", "Minh Tuấn", "Thùy Chi", "John Wick", "Ellen Joe"};
        String[] statuses = {"COMPLETED", "PROCESSING", "PENDING", "CANCELLED"};
        Random random = new Random();

        for (int i = 1; i <= 50; i++) {
            int id = 9000 + i;
            String customer = customers[random.nextInt(customers.length)];
            String status = statuses[random.nextInt(statuses.length)];
            double total = 100 + (1900 * random.nextDouble());
            long randomTime = System.currentTimeMillis() - (long) random.nextInt(30) * 24 * 60 * 60 * 1000;
            Date date = new Date(randomTime);
            list.add(new RecentBill(id, customer, date, total, status));
        }
        list.sort((b1, b2) -> Integer.compare(b2.getBillId(), b1.getBillId()));
        return list;
    }
}
