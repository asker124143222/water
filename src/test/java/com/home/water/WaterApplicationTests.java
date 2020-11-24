package com.home.water;

import com.home.water.bean.King;
import com.home.water.config.CustomParams;
import com.home.water.dao.DeptDao;
import com.home.water.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

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
    void printEnv(){
        Environment environment = applicationContext.getEnvironment();
        String[] defaultProfiles = environment.getDefaultProfiles();
        System.out.println(Arrays.toString(defaultProfiles));
        String value = environment.getProperty("mybatis.mapper-locations");
        System.out.println(value);

    }

    @Test
    void testEnum(){
        List<Dept> depts = deptDao.queryAll(null);
        System.out.println(depts);
    }

    @Test
    void testAnnotation(){
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        Set<String> annotationList = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            PreAuthorize methodAnnotation = handlerMethod.getMethodAnnotation(PreAuthorize.class);
            if (null != methodAnnotation) {
                System.out.println(infoEntry.getKey().getPatternsCondition().getPatterns()+" -- key:"+infoEntry.getKey()+" -- value: "+infoEntry.getValue().toString());
                annotationList.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            }
        }
    }
}


