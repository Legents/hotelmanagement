package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.User;
import com.example.hotelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("User")
    private UserService userSerivce;
    @RequestMapping("/queryAll")
    public List<User> queryAll(){
        List<User> users=userSerivce.queryAll();
        return users;
    }
    @RequestMapping("/changePwd")
    public Map<String,Object> change(@RequestBody User user){
        System.out.println(user);
        Map map=new HashMap();
        int state=0;//0:成功，1：失败
        int i=userSerivce.changePwd(user);
        if(i==0){
            state=1;
        }
        map.put("state",state);
        return map;
    }
    @RequestMapping("/queryByPhone")
    public Map<String,Object> queryByPhone(HttpSession session){
        //验证登录
        System.out.println("queryByPhone");
        Map map=new HashMap();
        int state=0;//0:成功，1：失败
        User item=userSerivce.queryByPhone(session.getAttribute("phone").toString());
        if(item==null){
            state=1;
        }
        map.put("state",state);
        map.put("user",item);
        return map;
    }
    /*@RequestMapping("/queryByPhone")
    public User queryByPhone(int phone){

    }*/

}
