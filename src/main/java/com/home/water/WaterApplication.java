package com.home.water;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.home.water.dao")
public class WaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterApplication.class, args);
    }

}
