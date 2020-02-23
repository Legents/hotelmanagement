package com.example.hotelmanagement.serviceImpl;

import com.example.hotelmanagement.entity.Waiter;
import com.example.hotelmanagement.mapper.WaiterMapper;
import com.example.hotelmanagement.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "waiter")
public class WaiterServiceImpl implements WaiterService {
    @Autowired
    private WaiterMapper waiterMapper;
    @Override
    public Waiter queryByAccount(String account) {
        return waiterMapper.queryByAccount(account);
    }
}
