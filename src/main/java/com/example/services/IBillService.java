package com.example.services;

import com.example.DTO.RecentBill;

import java.util.List;

public interface IBillService {
    public List<RecentBill> getRecentBills(int limit);
}
