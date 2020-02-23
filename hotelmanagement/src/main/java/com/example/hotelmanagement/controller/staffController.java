package com.example.hotelmanagement.controller;


import com.example.hotelmanagement.entity.Waiter;
import com.example.hotelmanagement.service.staffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class staffController {

    @Autowired
    private staffService staffservice;

    @PostMapping("/addstaff")
    public ModelAndView AddStaff(@ModelAttribute Waiter waiter){
        ModelAndView mav=new ModelAndView();

        int i=staffservice.queryStaff(waiter);
        if(i>0){
            mav.addObject("msg","该用户已存在");
            mav.addObject("state","failed");
        }
        else {
            //mav.addObject("state","success");
            if(staffservice.addStaff(waiter)!=0){
                mav.addObject("state","success");
            }
        }
        mav.setViewName("add_staff");
        return mav;
    }

    @RequestMapping("/viewstaff")
    public ModelAndView ViewStaff(){
        ModelAndView mav=new ModelAndView();
        List<Waiter> list=staffservice.viewAllStaff();
        mav.addObject("list",list);
        mav.setViewName("all_staffs");
        return mav;
    }

    @RequestMapping("/delete/{account}")
    public ModelAndView Delete(@PathVariable String account){

        ModelAndView mav=new ModelAndView();
        try{
            int i=staffservice.delete(account);
            mav.addObject("state","success");

        }catch (Exception e){
            mav.addObject("state","failed");
        }
        List<Waiter> list=staffservice.viewAllStaff();
        mav.addObject("list",list);
        mav.setViewName("all_staffs");
        return mav;
    }

    @RequestMapping("/edit/{account}")
    public ModelAndView Edit(@PathVariable String account){
        ModelAndView mav=new ModelAndView();
        Waiter wa1=staffservice.editStaff(account);
        mav.addObject("Waiter",wa1);
        mav.setViewName("edit_staff");
        return mav;
    }

    @PostMapping("editsubmit")
    public ModelAndView EditSubmit(@ModelAttribute Waiter waiter){
        ModelAndView mav=new ModelAndView();
        //int i=staffservice.queryStaff(waiter);
        int i=staffservice.editSubmit(waiter);
        if (i>0){
            mav.addObject("state","edit");
        }else{
            mav.addObject("state","editfailed");
        }
        List<Waiter> list=staffservice.viewAllStaff();
        mav.addObject("list",list);
       // mav.addObject("state","edit");
        mav.setViewName("all_staffs");
        return mav;
    }

    @RequestMapping("/addpage")
    public String page1(){
        return "add_staff";
    }

    @RequestMapping("/editpage")
    public String page2(){
        return "edit_staff";
    }

}
