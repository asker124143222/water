package com.home.water.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

/**
 * @Author: xu.dm
 * @Date: 2020/11/24 15:44
 * @Version: 1.0
 * @Description: TODO
 **/
@Configuration
@DependsOn("test2Config")
public class Test1Config {
    public Test1Config() {
        System.out.println("Test1Config 构造");
    }

    @Bean
    public Apple apple(){
        System.out.println("准备new Apple ...");
        return new Apple();
    }
}
