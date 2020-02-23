package com.example.hotelmanagement.mapper;


import com.example.hotelmanagement.entity.RoomType;

import java.util.List;

public interface RoomTypeMapper {
    public RoomType queryPriceByType(String typeName);
    public List<RoomType> queryAll();
}
