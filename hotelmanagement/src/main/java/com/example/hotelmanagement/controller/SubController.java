package com.example.hotelmanagement.controller;


import com.example.hotelmanagement.entity.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SubController {

    @Autowired
    public SimpMessagingTemplate template;

    @MessageMapping("/queue")
    public void queue(ResponseMessage rm){
        //System.out.println("进入方法");
        /**
         * 广播使用convertAndSendToUser方法，第一个参数为用户id，此时js中的订阅地址为
         * /user/+用户id+/message,其中/user/是固定的
         */
        //if(rm.getId().equals("zhangsan")){
        String id=rm.getId();
            template.convertAndSendToUser(id,"/message",rm.getContent());
        //}
        //else{
            //template.convertAndSendToUser("lisi","/message",rm.getContent());
        //}

        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
