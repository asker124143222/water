package com.home.water.config;

import com.home.water.entity.UserInfo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: xu.dm
 * @Date: 2020/3/27 23:07
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "custom")
@Data
public class CustomParams {
    private final static ThreadLocal<UserInfo> CUSTOM_THREAD_LOCAL = new ThreadLocal<>();
    private String sessionId;
    private boolean redis;
    private String redisURL;
    private boolean activeMQ;
    private boolean userInfo;
    private boolean echCacheConfig;
    private int version=100;

    public UserInfo getCustomUserInfo(){
        UserInfo userInfo = CUSTOM_THREAD_LOCAL.get();
        if(userInfo == null){
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    public void setCustomUserInfo(UserInfo userInfo){
        CUSTOM_THREAD_LOCAL.set(userInfo);
    }
}
