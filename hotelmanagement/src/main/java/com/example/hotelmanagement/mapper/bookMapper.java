package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.entity.book;
import com.example.hotelmanagement.entity.remainRoom;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface bookMapper {
    //查询所有房间的余量当前日期
    public remainRoom queryRemainByDate(Date date, String typeName);
    public remainRoom queryRemainByName(String typeName);


    public int sumPrice(@Param("phone") String phone);

    public int bookFailed(@Param("book1") book book1,@Param("waiter") String waiter);

    public int changeBookState(int bookId);

    public book queryBookByBookId(int bookId);
    public List<book> queryAll();/*预定成功的和待处理的*/

    public int bookToHistory(String bookId,Date arrive,Date depart,float money,String phone,String state);
    public int deleteBook(int bookId);

    public int payOrder(int bookId);
    //通过用户手机号查询订单
    public List<book> queryBookByPhone(String phone);
    public List<book> queryBookByPhoneAndArrive(String phone, Date arrive);
    //添加预定记录
    public int  addOrder(book book);
}
