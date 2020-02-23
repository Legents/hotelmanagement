package com.example.hotelmanagement.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class book implements Serializable {
    int bookId;
    String roomType;
    int roomNumber;
    Date arrive;
    Date depart;
    float orderPrice;
    float payState;
    String response;
    String phone;



}
