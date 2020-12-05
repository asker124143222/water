package com.home.water.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: xu.dm
 * @Date: 2020/11/24 16:05
 * @Version: 1.0
 * @Description: TODO
 **/
@Component
@Order(160)
public class AppleCommand implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("AppleCommand run ...");
    }
}
