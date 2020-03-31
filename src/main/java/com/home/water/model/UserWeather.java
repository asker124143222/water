package com.home.water.model;

import lombok.Data;

/**
 * @Author: xu.dm
 * @Date: 2020/3/31 15:36
 * @Version: 1.0
 * @Description: TODO
 **/
@Data
public class UserWeather {
    int userid;
    String userName;
    String name;
    int weatherYear;
    int avgValue;
}
