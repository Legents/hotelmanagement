package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.book;
import com.example.hotelmanagement.entity.remainRoom;

import java.util.Date;
import java.util.List;

public interface bookService {
    public remainRoom queryRemainByDate(Date date, String typeName);
    public remainRoom queryRemainByName(String typeName);


    public int sumPrice(String phone);
    public int changeBookState(int bookId);
    public int bookFailed(book book1, String waiter);

    public book queryBookByBookId(int bookId);
    public List<book> queryAll();
    public int bookToHistory(String bookId,Date arrive,Date depart,float money,String phone,String state);
    public int deleteBook(int bookId);


    public int payOrder(int bookId);

    public List<book> queryBookByPhone(String phone);
    public int addOrder(book book);
}
