package com.example.hotelmanagement.serviceImpl;

import com.example.hotelmanagement.entity.RoomType;

import com.example.hotelmanagement.mapper.RoomTypeMapper;

import com.example.hotelmanagement.service.RoomTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "RoomType")
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeMapper roomTypeMapper;
    @Override
    public float queryPriceByType(String typeName) {
        RoomType roomType=roomTypeMapper.queryPriceByType(typeName);

        return roomType.getTypePrice();
    }

    @Override
    public List<RoomType> queryAll() {
        return roomTypeMapper.queryAll();
    }
}
