package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.User;
import com.example.hotelmanagement.entity.Waiter;
import com.example.hotelmanagement.service.UserService;
import com.example.hotelmanagement.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    @Qualifier("User")
    private UserService userSerivce;

    @Autowired
    @Qualifier("waiter")
    private WaiterService waiterService;


    @PostMapping("/login1")
    @ResponseBody
    public Map<String,Object> userLogin(@RequestBody User user, HttpSession session){
        //验证登录
        Map map=new HashMap<String,Object>();
        int state=0;//0:成功，1：失败
        String msg="";
        User item=userSerivce.queryByPhone(user.getPhone());
        System.out.println(user);
        if(item==null){
            msg="不存在该用户";
            state=1;
        }else {
            if (!(item.getPhone().equals(user.getPhone())) || !(item.getPassword().equals(user.getPassword()))) {
                msg = "用户名或密码不正确";
                state = 1;
            }
        }
        if(state==0){
            session.setAttribute("phone",user.getPhone());
            //新增
            session.setAttribute("account1",user.getPhone());
            System.out.println("保存session:"+session.getAttribute("phone"));
        }
        map.put("msg",msg);
        map.put("state",state);
        return map;
    }
    @PostMapping("/login2")
    @ResponseBody
    public Map<String,Object> waiterLogin(@RequestBody Waiter waiter, HttpSession session){
        //验证登录
        Map map=new HashMap<String,Object>();
        int state=0;//0:成功，1：失败
        String msg="";
        Waiter item=waiterService.queryByAccount(waiter.getAccount());
        System.out.println(waiter);
       /* System.out.println(waiter.getAccount());*/
        if(item==null){
            msg="不存在该服务员";
            state=1;
        }else {
            if (!(item.getAccount().equals(waiter.getAccount())) || !(item.getPassword().equals(waiter.getPassword()))) {
                msg = "用户名或密码不正确";
                state = 1;
            }
        }
        if(state==0){
            session.setAttribute("account",waiter.getAccount());
            System.out.println("保存account session:"+session.getAttribute("account"));
        }
        map.put("msg",msg);
        map.put("state",state);
        return map;
    }

    @RequestMapping("/register")
    @ResponseBody
    public Map<String,Object> test2(@RequestBody User user){
        System.out.println(user);
        Map map=new HashMap<String,Object>();
        String msg="注册成功";
        int state=0;
        User item=userSerivce.queryByPhone(user.getPhone());
        if(item!=null){
            msg="该手机号已被注册";
            state=1;
        }else {
            User item2 = userSerivce.queryByIdNumber(user);
            if (item2 != null) {
                msg = "该身份证号已被注册";
                state = 1;
            }else{
                int i=userSerivce.insert(user);
                if(i==0){
                    msg="数据库故障，注册失败";
                    state=1;
                }
            }
        }
        map.put("state",state);
        map.put("msg",msg);
        return map;
    }
}
