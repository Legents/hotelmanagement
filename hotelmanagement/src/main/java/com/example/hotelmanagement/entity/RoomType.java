package com.example.hotelmanagement.entity;

import lombok.Data;

@Data
public class RoomType {
    int typeId;
    String typeName;
    float typePrice;
    int typeArea;
    int typeNumber;
    int numberOfPeople;
    int numberOfBed;
    int cancel;
    int breakfast;
    String photo;
}
