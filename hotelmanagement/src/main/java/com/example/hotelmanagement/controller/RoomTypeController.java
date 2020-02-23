package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.RoomType;
import com.example.hotelmanagement.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roomType")
public class RoomTypeController {
    @Autowired
    @Qualifier("RoomType")
    private RoomTypeService roomTypeService;
    @RequestMapping("/queryAll")
    public Map<String,Object> queryAll(){
        System.out.println("queryAll");
        Map map=new HashMap<String,Object>();
        int state=0;
        List<RoomType> roomList=roomTypeService.queryAll();
        System.out.println(roomList);
        if(roomList.isEmpty()) {
            state = 1;
        }
        map.put("roomList",roomList);
        map.put("state",state);
        return map;
    }
}
