package com.example.hotelmanagement.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class remainRoom implements Serializable {
    private String typeName;
    private int typeNumber;

}
