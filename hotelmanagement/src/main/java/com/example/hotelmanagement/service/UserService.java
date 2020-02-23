package com.example.hotelmanagement.service;

import com.example.hotelmanagement.entity.User;

import java.util.List;

public interface UserService {
    public List queryAll();
    public User queryByPhone(String phone);
    public int insert(User user);
    public User queryByIdNumber(User user);
    public int changePwd(User user);
    public int changeLevel(String phone,int level);
}
