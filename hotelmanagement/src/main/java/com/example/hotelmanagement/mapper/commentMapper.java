package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.entity.book;
import com.example.hotelmanagement.entity.comment;


import java.util.Date;
import java.util.List;

public interface commentMapper {
    public int addComment(String content, int star, String phone);
    public List<comment> queryComment();
}
