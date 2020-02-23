package com.example.hotelmanagement.config.MvcConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig  implements WebMvcConfigurer {
 /*   public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor1())
                .addPathPatterns("/**")//拦截的路径
                .excludePathPatterns("/","/login.html","/user/login");//排除的路径
    }*/
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");//路径映射
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/sign_up.html").setViewName("sign_up");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/user.html").setViewName("user");
        registry.addViewController("/admin.html").setViewName("admin");
        registry.addViewController("/new_booking.html").setViewName("new_booking");
        registry.addViewController("/userViewBooking.html").setViewName("userViewBooking");
        registry.addViewController("/userAllVehicles.html").setViewName("userAllVehicles");
        registry.addViewController("/userNewBooking.html").setViewName("userNewBooking");
        registry.addViewController("/changePassword.html").setViewName("changePassword");
        registry.addViewController("/view_booking.html").setViewName("view_booking");
        registry.addViewController("/backRoom.html").setViewName("backRoom");
        registry.addViewController("/addRoom.html").setViewName("addRoom");
        registry.addViewController("/allRoom.html").setViewName("allRoom");

        registry.addViewController("/add_room.html").setViewName("add_room");
        registry.addViewController("/all_rooms.html").setViewName("all_rooms");
        registry.addViewController("/edit_room.html").setViewName("edit_room");
        registry.addViewController("/chat.html").setViewName("chat");
        registry.addViewController("/chat2.html").setViewName("chat2");
    }
}
