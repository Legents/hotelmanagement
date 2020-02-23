package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.book;
import com.example.hotelmanagement.entity.comment;

import java.util.List;

public interface commentService {
    public int addComment(String content, int star, String phone);
    public List<comment> queryAllComment();
}
