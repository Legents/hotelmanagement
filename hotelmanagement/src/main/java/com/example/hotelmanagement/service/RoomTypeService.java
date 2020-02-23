package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.RoomType;

import java.util.List;

public interface RoomTypeService {
    public float queryPriceByType(String typeName);
    public List<RoomType> queryAll();
}
