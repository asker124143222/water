package com.home.water.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.Priority;

/**
 * @Author: xu.dm
 * @Date: 2020/11/24 15:44
 * @Version: 1.0
 * @Description: TODO
 **/
@Configuration
public class Test2Config {

    public Test2Config() {
        System.out.println("Test2Config 构造");
    }

    @Bean
    public Orange orange(){
        System.out.println("准备new Orange ...");
        return new Orange();
    }
}
