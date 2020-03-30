package com.home.water;

import com.home.water.bean.King;

import com.home.water.config.CustomParams;
import com.home.water.entity.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class WaterApplicationTests {
    @Autowired
    King king;

    @Autowired
    CustomParams customParams;

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(king);
        System.out.println(customParams);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();

    }

}
