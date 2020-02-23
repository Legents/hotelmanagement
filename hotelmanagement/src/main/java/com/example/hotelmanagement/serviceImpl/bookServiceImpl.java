package com.example.hotelmanagement.serviceImpl;

import com.example.hotelmanagement.entity.book;
import com.example.hotelmanagement.entity.remainRoom;
import com.example.hotelmanagement.mapper.bookMapper;
import com.example.hotelmanagement.service.bookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service(value = "book")
public class bookServiceImpl implements bookService {

    @Autowired
    private bookMapper bookMapper;

    @Override
    public remainRoom queryRemainByDate(Date date, String typeName) {
        System.out.println(typeName+date);
        remainRoom  r=bookMapper.queryRemainByDate(date,typeName);
        if(r==null)
           return queryRemainByName(typeName);
        return r;
    }

    @Override
    public remainRoom queryRemainByName(String typeName) {
        return bookMapper.queryRemainByName(typeName);
    }


    @Override
    public int bookFailed(book book1, String waiter) {
        System.out.println(book1.getBookId());
        return bookMapper.bookFailed(book1,waiter);
    }


    @Override
    public int sumPrice(String phone) {
        return bookMapper.sumPrice(phone);
    }

    @Override
    public int changeBookState(int bookId) {
        return bookMapper.changeBookState(bookId);
    }

    @Override
    public List<book> queryBookByPhone(String phone) {
        return bookMapper.queryBookByPhone(phone);
    }

    @Override
    public int addOrder(book book) {
        return bookMapper.addOrder(book);
    }
    @Override
    public int payOrder(int bookId) {
        return bookMapper.payOrder(bookId);
    }

    @Override
    public int bookToHistory(String bookId, Date arrive, Date depart, float money, String phone, String state) {
        return bookMapper.bookToHistory(bookId, arrive, depart, money, phone, state);
    }

    @Override
    public int deleteBook(int bookId) {
        return bookMapper.deleteBook(bookId);
    }

    @Override
    public book queryBookByBookId(int bookId) {
        return bookMapper.queryBookByBookId(bookId);
    }

    @Override
    public List<book> queryAll() {
        return bookMapper.queryAll();
    }


}
