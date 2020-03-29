package com.home.water;

import com.home.water.bean.King;

import com.home.water.config.CustomParams;
import com.home.water.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class WaterApplicationTests {
    @Autowired
    King king;

    @Autowired
    CustomParams customParams;

    @Test
    void contextLoads() {
        System.out.println(king);
        System.out.println(customParams);

    }

}
