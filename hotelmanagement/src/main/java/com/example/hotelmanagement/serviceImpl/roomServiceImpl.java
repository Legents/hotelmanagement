package com.example.hotelmanagement.serviceImpl;

import com.example.hotelmanagement.entity.room;
import com.example.hotelmanagement.mapper.roomMapper;
import com.example.hotelmanagement.service.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "room")
public class roomServiceImpl implements roomService {

    @Autowired
    private roomMapper roomMapper;

    @Override
    public List<room> queryAllRoom() {
        return roomMapper.queryAllRoom();
    }

    @Override
    public int queryTypeIdByName(String roomType) {
        return roomMapper.queryTypeIdByName(roomType);
    }

    @Override
    public int addRoom(String roomId, int roomType) {
        return roomMapper.addRoom(roomId,roomType);
    }

    @Override
    public int addRoomNumber(int typeId) {
        return roomMapper.addRoomNumber(typeId);
    }

    @Override
    public List<String> queryAllId() {
        return roomMapper.queryAllId();
    }
}
