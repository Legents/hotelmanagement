package com.example.hotelmanagement.entity;

import lombok.Data;

@Data
public class User {
    private String phone;
    private String name;
    private String password;
    private int level;
    private String idNumber;
}
