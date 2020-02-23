package com.example.hotelmanagement.controller;


import com.example.hotelmanagement.entity.comment;
import com.example.hotelmanagement.entity.room;
import com.example.hotelmanagement.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 执行评论操作
 * 添加评论和查询评论
 */
@RestController
@RequestMapping("/comment")
public class commentController {

    @Autowired
    @Qualifier("comment")
    private commentService commentService;

    /**
     * 添加一条评论
     * @param content 评论内容
     * @param star  星级
     * @param phone 评论人电话
     * @return 添加结果
     */
    @RequestMapping("/addComment")
    public Map<String,Object> addComment(@RequestParam String content,int star,String phone){
        Map map=new HashMap<String,Object>();
        int state=0;
        int result = commentService.addComment(content,star,phone);
        if(result==0){
            state=1;
        }
        map.put("state",state);
        return map;
    }

    /**
     * 查询所有评论
     * @return
     */
    @RequestMapping("/queryAllComment")
    public Map<String,Object> queryAllComment(){
        Map map=new HashMap<String,Object>();
        int state=0;
        List<comment> commentList= commentService.queryAllComment();
        if(commentList.isEmpty()){
            state=1;
        }
        map.put("state",state);
        map.put("commentList",commentList);
        return map;
    }
}
