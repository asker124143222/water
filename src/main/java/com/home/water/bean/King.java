package com.home.water.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: xu.dm
 * @Date: 2020/3/27 22:35
 * @Description:
 */
@Component
@PropertySource("classpath:custom.properties")
@ConfigurationProperties(prefix = "king")
@Data
public class King {
    private String name;
    private int age;
    private int attack;
    private int defense;
    private long weight;
    private int height;
    private Date occurrenceDate;
    private List<String> skills;
    private Map<String,Object> extendAttr;
}
