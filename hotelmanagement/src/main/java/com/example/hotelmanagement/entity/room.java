package com.example.hotelmanagement.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class room implements Serializable {
    String roomId;
    int roomType;
    int state;


}
