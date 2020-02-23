package com.example.hotelmanagement.entity;

import lombok.Data;

import java.sql.Date;

@Data
public class checkIn {
    String bookId;
    Date arrive;
    Date depart;
    float money;
    String state;
    String roomId;
    String phone;
    String waiter;

}
