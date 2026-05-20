package com.vti.lesson1.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration //de nhan dien la 1 lop cau hinh
//cau hinh de su dung models mapper
public class DIContainer {
    @Bean //day la 1 phuong thuc san xuat ra doi tuong
    public ModelMapper modelMapper(){
        return new ModelMapper(); // cau hinh tao ra model mapper khi chay ung dung
    }
}
