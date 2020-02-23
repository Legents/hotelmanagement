package com.example.hotelmanagement.service;


import com.example.hotelmanagement.entity.Waiter;

import java.util.List;

public interface staffService {
    int queryStaff(Waiter wa1);
    int addStaff(Waiter wa1);
    List<Waiter> viewAllStaff();
    int delete(String account);
    Waiter editStaff(String account);
    int editSubmit(Waiter wa1);
}
