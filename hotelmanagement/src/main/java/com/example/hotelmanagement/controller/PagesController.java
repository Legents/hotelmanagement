/*
package com.example.hotelmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PagesController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }
    @RequestMapping("/sign_up")
    public String signUp(){
        return "sign_up";
    }

    @RequestMapping("/index")
    public String index(HttpSession session){
        System.out.println("获取session:"+session.getAttribute("phone"));
        return "index";
    }
    @RequestMapping("/user")
    public String user(){
        return "user";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
*/
