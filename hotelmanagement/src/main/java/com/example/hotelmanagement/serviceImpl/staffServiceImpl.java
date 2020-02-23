package com.example.hotelmanagement.serviceImpl;

import com.example.hotelmanagement.entity.Waiter;
import com.example.hotelmanagement.mapper.staffMapper;
import com.example.hotelmanagement.service.staffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class staffServiceImpl implements staffService {

    @Autowired
    private staffMapper staffmapper;

    @Override
    public int queryStaff(Waiter wa1){
        return staffmapper.queryStaff(wa1);
    }

    public int addStaff(Waiter wa1){
        return staffmapper.addStaff(wa1);
    }

    public List<Waiter> viewAllStaff(){
        return staffmapper.viewAllStaff();
    }

    public int delete(String account){return staffmapper.delete(account);}

    public Waiter editStaff(String account){
        return staffmapper.editStaff(account);
    }

    public int editSubmit(Waiter wa1){return staffmapper.editSubmit(wa1);}


}
