package com.home.water.config;

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
    private boolean redis;
    private String redisURL;
    private boolean activeMQ;
    private boolean userInfo;
    private boolean echCacheConfig;
    private int version=100;
}
