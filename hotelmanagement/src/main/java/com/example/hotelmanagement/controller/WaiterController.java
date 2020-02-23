package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.entity.User;
import com.example.hotelmanagement.entity.Waiter;
import com.example.hotelmanagement.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/waiter")
public class WaiterController {
    @Autowired
    @Qualifier("waiter")
    private WaiterService waiterService;

    @RequestMapping("/queryByAccount")
    @ResponseBody
    public Map<String,Object> queryByAccount(HttpSession session){
        System.out.println("queryByAccount");
        Map map =new HashMap<String,Object>();
        int state=0;//0:成功，1：失败
        Waiter item=waiterService.queryByAccount(session.getAttribute("account").toString());
        if(item==null){
            state=1;
        }
        map.put("state",state);
        map.put("waiter",item);
        return map;
    }
}
