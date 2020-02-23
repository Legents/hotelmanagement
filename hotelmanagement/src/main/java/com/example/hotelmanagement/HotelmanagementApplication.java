package com.example.hotelmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.hotelmanagement.mapper")
public class HotelmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelmanagementApplication.class, args);
    }

}
