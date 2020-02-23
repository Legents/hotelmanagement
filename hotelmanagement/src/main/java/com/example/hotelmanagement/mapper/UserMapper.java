package com.example.hotelmanagement.mapper;

import com.example.hotelmanagement.entity.User;

import java.util.List;

public interface UserMapper {
    public List<User> queryAll();
    public User queryByPhone(String phone);
    public int insert(User user);
    public User queryByIdNumber(User user);
    public int changePwd(User user);
    public int changeLevel(String phone,int level);
}
