package com.example.hotelmanagement.serviceImpl;

import com.example.hotelmanagement.entity.comment;
import com.example.hotelmanagement.mapper.commentMapper;
import com.example.hotelmanagement.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "comment")
public class commentServiceImpl implements commentService {
    @Autowired
    private commentMapper commentMapper;
    @Override
    public int addComment(String content, int star, String phone) {
        int i=commentMapper.addComment(content,star,phone);
        return i;
    }

    @Override
    public List<comment> queryAllComment() {
        return commentMapper.queryComment();
    }
}
