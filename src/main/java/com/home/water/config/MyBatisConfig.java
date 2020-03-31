package com.home.water.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xu.dm
 * @Date: 2020/3/31 10:19
 * @Version: 1.0
 * @Description: TODO
 **/
@Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
               configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
