package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.User;
import com.example.hotelmanagement.entity.book;
import com.example.hotelmanagement.entity.checkIn;
import com.example.hotelmanagement.entity.room;
import com.example.hotelmanagement.service.UserService;
import com.example.hotelmanagement.service.bookService;
import com.example.hotelmanagement.service.checkInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理开房请求
 */
@RestController
@RequestMapping("/check")
public class CheckInController {
    @Autowired
    @Qualifier("checkIn")
    private checkInService checkInService;

    @Autowired
    @Qualifier("book")
    private  bookService bookService;

    @Autowired
    @Qualifier("User")
    private UserService userService;

    @RequestMapping("/queryAll")
    public Map<String,Object> queryAll(){
        Map map=new HashMap();
        int state=0;
        List<checkIn> checkInList=checkInService.queryAll();
        if(checkInList.isEmpty()){
            state=1;
        }
        map.put("state",state);
        map.put("checkInList",checkInList);
        return map;
    }

    /**
     * 用户办理入住时将订单信息从book表迁移到checkIn表
     * @param bookId 用户
     * @param waiter 服务员
     */
    @RequestMapping("/checkIn")
    public Map<String,Object> bookToCheckIn(int bookId, String waiter){
        System.out.println(bookId);
        Map map =new HashMap();
        int state=0;
        book b = bookService.queryBookByBookId(bookId);
        System.out.println(b);
        String roomType=b.getRoomType();
        Date arrive=b.getArrive();
        Date depart=b.getDepart();
        float money=b.getPayState();
        int roomNumber=b.getRoomNumber();
        String roomId=new String();
        String phone = b.getPhone();
        //获取到对应数量的空房间号
        List<room> roomList=checkInService.getRoom(roomType,roomNumber);
        for(room r:roomList){
            roomId = roomId +' '+ r.getRoomId();
            checkInService.changeStateToTrue(r.getRoomId());
        }
        checkInService.bookToCheckIn(String.valueOf(bookId),arrive,depart,money,roomId,phone,waiter);
       int i= checkInService.deleteBook(bookId);
        if(i==0){
            state=1;
        }
        map.put("state",state);
        return map;
    }

    /**
     * 退房时将信息迁移到历史记录表内
     * @param roomId
     * @param checkOutWaiter
     */
    @RequestMapping("/checkOut")
    public Map<String,Object> checkInToCheckOut(@RequestParam String roomId,String checkOutWaiter){

        System.out.println(roomId);
        System.out.println(checkOutWaiter);
        Map map=new HashMap();
        int state1=0;
        int levelMsg=0;   //提示用户升级
        //首先获取到在住信息
        roomId='%'+roomId+'%';
        List<checkIn> checkInList = checkInService.getCheckIn(roomId);
        //修改房间状态
        String roomId1 = checkInList.get(0).getRoomId().trim();
        String [] spString = roomId1.split(" ");
        for(String ss : spString) {  System.out.println(ss);}

        int i=0;
        for(String ss : spString) {
            if(ss!=""||ss!=" ") {
                System.out.println(ss);
                i = checkInService.changeStateToFalse(ss);
                System.out.println("i:" + i);
                if (i == 0) {
                    state1 = 1;
                    break;
                }
            }
        }
        if(i!=0) {
            //迁移到历史表中
            String bookId = checkInList.get(0).getBookId();
            Date arrive = checkInList.get(0).getArrive();
            Date depart = checkInList.get(0).getDepart();
            float money = checkInList.get(0).getMoney();
            String phone = checkInList.get(0).getPhone();
            String waiter = checkInList.get(0).getWaiter();
            String state = "正常入住";
           int j= checkInService.toCheckOut(bookId, arrive, depart, money, state, roomId1, phone, waiter, checkOutWaiter);
            System.out.println("j:"+j);
           if(j!=0){
               //从在住表中删除
             int k= checkInService.deleteCheckIn(bookId);
               System.out.println("k:"+k);
             if(k==0){
                 state1=1;
             }else{
                 int sum=bookService.sumPrice(phone);
                 User user=userService.queryByPhone(phone);
                 System.out.println(sum);
                 if(sum>=500&&sum<1000){
                     if(user.getLevel()<2){
                         int m=userService.changeLevel(phone,2);
                         if(m>0) {
                             levelMsg = 2;
                         }
                     }
                 }else if(sum>=1000){
                     if(user.getLevel()<3){
                         int m=userService.changeLevel(phone,3);
                         if(m>0){
                             levelMsg=3;
                         }
                     }
                 }
             }
           }else{
               state1=1;
           }
        }else{
            state1=1;
        }
        map.put("state",state1);
        map.put("levelMsg",levelMsg);
        return map;
    }
}
