package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.checkIn;
import com.example.hotelmanagement.entity.room;

import java.util.Date;
import java.util.List;

public interface checkInService {
    //查询到对应数量所需要的房间号
    public List<room> getRoom(String roomType, int roomNumber);
    public List<checkIn> getCheckIn(String roomId);
    public int changeStateToTrue(String roomId);
    public int changeStateToFalse(String roomId);
    public int bookToCheckIn(String bookId, Date arrive, Date depart, float money, String roomId, String phone, String waiter);
    public int toCheckOut(String bookId, Date arrive, Date depart, float money, String state, String roomId, String phone, String waiter, String checkOutWater);
    public int deleteBook(int bookId);
    public int deleteCheckIn(String bookId);
    public List<checkIn> queryAll();
}
