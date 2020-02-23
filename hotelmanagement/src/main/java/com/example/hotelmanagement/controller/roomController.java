package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.room;
import com.example.hotelmanagement.service.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理房间相关操作
 */
@RestController
@RequestMapping("/room")
public class roomController {

    @Autowired
    @Qualifier("room")
    private roomService roomService;
    /**
     * 查询所有房间
     * @return
     */
    @RequestMapping("/queryAllRoom")
    public Map<String,Object> queryAllRoom(){
        Map map=new HashMap<String,Object>();
        List<room> roomList= roomService.queryAllRoom();
        map.put("roomList",roomList);
        return map;
    }

    /**
     * 在房间列表中添加对应房间之后再类型表里增加数量
     * @param roomId
     * @param typeName
     * @return -1表示该房间号已存在
     *          其他表示可以插入成功
     */
    @RequestMapping("/addRoom")
    public Map<String,Object> addRoom(String roomId,int typeId){
        Map map =new HashMap();
        int state=0;
        List<String> idList=roomService.queryAllId();
        for(String id:idList){
            if(roomId.equals(id))
                state=1;
        }
        if(state==0) {
            roomService.addRoom(roomId, typeId);
            int i = roomService.addRoomNumber(typeId);
            if (i == 0) {
                state = 1;
            }
        }
        map.put("state",state);
       return map;

    }

}
