package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.room;

import java.util.List;

public interface roomService {
    public List<room> queryAllRoom();
    public int queryTypeIdByName(String roomType);
    public int addRoom(String roomId, int roomType);
    public int addRoomNumber(int typeId);
    public List<String> queryAllId();
}
