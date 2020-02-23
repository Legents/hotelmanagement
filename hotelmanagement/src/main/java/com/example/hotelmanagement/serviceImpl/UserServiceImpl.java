package com.example.hotelmanagement.serviceImpl;

import com.example.hotelmanagement.entity.User;
import com.example.hotelmanagement.mapper.UserMapper;
import com.example.hotelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "User")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public User queryByPhone(String phone) {
        return userMapper.queryByPhone(phone);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User queryByIdNumber(User user) {
        return userMapper.queryByIdNumber(user);
    }

    @Override
    public int changePwd(User user) {
        return userMapper.changePwd(user);
    }

    @Override
    public int changeLevel(String phone, int level) {
        return userMapper.changeLevel(phone,level);
    }
}
