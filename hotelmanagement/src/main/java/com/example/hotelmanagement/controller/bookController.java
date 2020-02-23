package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.book;
import com.example.hotelmanagement.entity.remainRoom;

import com.example.hotelmanagement.service.RoomTypeService;
import com.example.hotelmanagement.service.bookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理预定操作
 */
@RestController
@RequestMapping("/book")
public class bookController {
    @Autowired
    @Qualifier("book")
    private  bookService bookService;
    @Autowired
    @Qualifier("RoomType")
    private RoomTypeService roomTypeService;


    @RequestMapping("/queryRemainByDate")
    public remainRoom queryRemainByDate(Date date1,String typeName) {
        remainRoom r = bookService.queryRemainByDate(date1,typeName);
        if(r==null)
            return bookService.queryRemainByName(typeName);
        return r;
    }



    /**
     * 预定失败时通过此方法将订单更新到历史列表里
     * 再从预定列表中删除
     * @param bookId
     * @return
     */
    @RequestMapping("/bookFailed")
    public Map<String,Object> bookFailed(@RequestParam int bookId,String waiter){
        Map map=new HashMap();
        int state=0;
        book book1 = bookService.queryBookByBookId(bookId);
        int i=bookService.bookFailed(book1,waiter);
        if(i!=0){
          int j=bookService.deleteBook(bookId);
          if(j==0){
              state=1;
          }
        }else{
            state=1;
        }
        map.put("state",state);
        return map;
    }


    @RequestMapping("/changeBookState")
    public Map<String,Object> changeBookState(int bookId){
        Map map=new HashMap<String,Object>();
        int state=0;
        int i=bookService.changeBookState(bookId);
        if(i==0){
            state=1;
        }
        map.put("state",state);
        return map;
    }


    @RequestMapping("/queryAll")
    public Map<String,Object> queryAll(){
        Map map=new HashMap();
        int state=0;
        List<book> bookList=bookService.queryAll();
        if(bookList.isEmpty()){
            state=1;
        }
        map.put("state",state);
        map.put("bookList",bookList);
        return map;
    }

    @RequestMapping("/bookToHistory")
    public Map<String,Object> bookToHistory(int bookId){
        Map map=new HashMap<String,Object>();
        int states=0;
        //首先获取订单信息
        book book = bookService.queryBookByBookId(bookId);
        String bookId1=String.valueOf(book.getBookId());
        Date arrive = book.getArrive();
        Date depart = book.getDepart();
        float money = book.getPayState();
        String phone = book.getPhone();
        String state = new String();
        if(book.getResponse().equals("待支付"))
            state = "取消订单";
        else if(book.getResponse().equals("待处理"))
            state = "用户退款";
        int j=bookService.bookToHistory(bookId1,arrive,depart,money,phone,state);
        if(j>0) {
            //删除预定表中信息
            int i = bookService.deleteBook(bookId);
            if (i == 0) {
                states = 1;
            }
        }else{
            states=1;
        }
        map.put("state",states);
        return map;
    }


    @RequestMapping("/payOrder")
    public Map<String,Object> payOrder(int bookId){
        Map map=new HashMap<String,Object>();
        int state=0;
        int i=bookService.payOrder(bookId);
        if(i==0){
            state=1;
        }
        map.put("state",state);
        return map;
    }



    /**
     * 先通过天数和房间数计算出金额
     * @param roomType 房间类型
     * @param roomNumber 房间数
     * @param arrive 到达时间
     * @param depart 离开时间
     * @param phone 用户电话
     * @return
     */
    @RequestMapping("/bookRoom")
    public Map<String,Object> bookRoom(@RequestBody book book, HttpSession session) throws ParseException {
        System.out.println(book);
        Map map=new HashMap<String,Object>();
        System.out.println(book.getArrive());
        int state=0;
        int errorMsg=0;
        remainRoom r = bookService.queryRemainByDate(book.getArrive(),book.getRoomType());
        System.out.println(r);
        if(r.getTypeNumber()<book.getRoomNumber()){
            errorMsg=r.getTypeNumber();
            state=2;//无余量
        }else {
            String phone = session.getAttribute("phone").toString();
            book.setPhone(phone);
            //获取入住天数
            int days = getDistanceOfDate(book.getArrive(), book.getDepart());
            System.out.println(days);
            //根据房型获取单价
//        roomTypeController rtc = new roomTypeController();
            float unitPrice = roomTypeService.queryPriceByType(book.getRoomType());
            //根据订单计算总价
            float orderPrice = unitPrice * days * book.getRoomNumber();
            book.setOrderPrice(orderPrice);
            float payState;
            if (days * book.getRoomNumber() <= 5)     //小于五天 原价支付
                payState = orderPrice;
            else if (days * book.getRoomNumber() <= 10) //5-10天 95折
                payState = (float) (orderPrice * 0.95);
            else if (days * book.getRoomNumber() <= 30)  //11-30天 9折
                payState = (float) (orderPrice * 0.9);
            else                //30天以上 8折
                payState = (float) (orderPrice * 0.8);

            book.setPayState(payState);
            //将信息写入预定表
            int i = bookService.addOrder(book);
            if (i == 0) {
                state = 1;
            }
        }
        map.put("state",state);
        map.put("errorMsg",errorMsg);
        return map;
    }

    /**
     *
     * 根据日期计算入住天数
     * @param arrive 到店时间
     * @param depart 离店时间
     * @return 入住天数
     * @throws ParseException
     */
    public int getDistanceOfDate(Date arrive, Date depart) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        long arrive1 = arrive.getTime();
        System.out.println(arrive1);
        long depart1 = depart.getTime();
        return (int)(depart1 - arrive1) / (1000 * 60 * 60 * 24);
    }


    /**
     * 客人到店时服务员通过手机号查询用户订单
     * @param phone 用户手机号
     * @return
     */
    @RequestMapping("/queryBookByPhone")
    public Map<String,Object> queryBookByPhone(String phone){
        Map map=new HashMap<String,Object>();
        int state=0;
        List<book> bookList= bookService.queryBookByPhone(phone);
        if(bookList.isEmpty()){
            state=1;
        }
        map.put("state",state);
        map.put("bookList",bookList);
        return map;
    }
}
