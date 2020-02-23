package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.entity.Waiter;

public interface WaiterMapper {
    public Waiter queryByAccount(String account);
}
