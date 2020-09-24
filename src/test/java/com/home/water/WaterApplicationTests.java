package com.home.water;

import com.home.water.bean.King;

import com.home.water.common.EntityStatus;
import com.home.water.config.CustomParams;
import com.home.water.dao.DeptDao;
import com.home.water.entity.Dept;
import com.home.water.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WaterApplicationTests {
    @Autowired
    King king;

    @Autowired
    CustomParams customParams;

    @Autowired
    DataSource dataSource;

    @Resource
    ApplicationContext applicationContext;

    @Resource
    DeptDao deptDao;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(king);
        System.out.println(customParams);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }

    @Test
    void myTest() {
        System.out.println("class:" + applicationContext.getClass() + "," + applicationContext.toString());
        String[] names = applicationContext.getBeanNamesForType(RedisConnectionFactory.class);
        for (String name : names) {
            System.out.println(name);
        }

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }

    }

    @Test
    void testEnum(){
        List<Dept> depts = deptDao.queryAll(null);
        System.out.println(depts);
    }
}


