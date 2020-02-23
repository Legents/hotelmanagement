package com.example.hotelmanagement.serviceImpl;

import com.example.hotelmanagement.entity.checkIn;
import com.example.hotelmanagement.entity.room;
import com.example.hotelmanagement.mapper.checkInMapper;
import com.example.hotelmanagement.service.checkInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "checkIn")
public class checkInServiceImpl implements checkInService {

    @Autowired
    private checkInMapper checkInMapper;

    @Override
    public List<room> getRoom(String roomType, int roomNumber) {
        return checkInMapper.getRoom(roomType,roomNumber);
    }

    @Override
    public List<checkIn> getCheckIn(String roomId) {
        System.out.println(checkInMapper.getCheckIn(roomId));
        return checkInMapper.getCheckIn(roomId);
    }

    @Override
    public int changeStateToTrue(String roomId) {
       return checkInMapper.changeStateToTrue(roomId);
    }

    @Override
    public int changeStateToFalse(String roomId) {
       return checkInMapper.changeStateToFalse(roomId);
    }

    @Override
    public int bookToCheckIn(String bookId, Date arrive, Date depart, float money, String roomId, String phone, String waiter) {
       return checkInMapper.bookToCheckIn(bookId,arrive,depart,money,roomId,phone,waiter);
    }

    @Override
    public int toCheckOut(String bookId, Date arrive, Date depart, float money, String state, String roomId, String phone, String waiter, String checkOutWaiter) {
      return  checkInMapper.toCheckOut(bookId,arrive,depart,money,state,roomId,phone,waiter,checkOutWaiter);
    }

    @Override
    public int deleteBook(int bookId) {
        return checkInMapper.deleteBook(bookId);
    }

    @Override
    public int deleteCheckIn(String bookId) {
        return checkInMapper.deleteCheckIn(bookId);
    }

    @Override
    public List<checkIn> queryAll() {
        return checkInMapper.queryAll();
    }
}
