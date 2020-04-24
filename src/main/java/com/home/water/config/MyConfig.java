package com.home.water.config;

import com.home.water.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: xu.dm
 * @Date: 2020/3/28 16:49
 * @Description:
 */
@Configuration
public class MyConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private CustomParams customParams;

    // bean对象名称缺省为方法名，即getAdminUserInfo，根据需要使用@Bean(name="")指定，可省略name
    @Bean(name="userInfo")
    //条件注入,主配置文件中有前缀为custom的根配置，并且配置项的名字为userInfo的配置为true
    @ConditionalOnProperty(prefix = "custom", name = "userInfo" ,havingValue = "true")
    public UserInfo getAdminUserInfo(){
        logger.info("初始化 ... getAdminUserInfo()");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(0);
        userInfo.setUserName("root");
        userInfo.setPassword("123@!@#");
        logger.info("自定义参数注入："+customParams.toString());
        return userInfo;
    }
}
