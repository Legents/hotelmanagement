package com.example.hotelmanagement.mapper;


import com.example.hotelmanagement.entity.Waiter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface staffMapper {
    int queryStaff(Waiter wa1);
    int addStaff(Waiter wa1);
    List<Waiter> viewAllStaff();
    int delete(String account);
    Waiter editStaff(String account);
    int editSubmit(Waiter wa1);
}
