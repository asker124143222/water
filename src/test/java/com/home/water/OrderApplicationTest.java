package com.home.water;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: xu.dm
 * @Date: 2020/11/24 15:49
 * @Version: 1.0
 * @Description: TODO
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderApplicationTest {
    @Test
    void myTest01(){
        System.out.println("启动测试。。。");
    }
}
