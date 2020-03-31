package com.home.water.model;

import lombok.Data;

/**
 * @Author: xu.dm
 * @Date: 2020/3/31 16:06
 * @Version: 1.0
 * @Description: TODO
 **/

@Data
public class WeatherUser {
    int id;
    int userid;
    String userName;
    int weatherYear;
    int avgValue;
}
