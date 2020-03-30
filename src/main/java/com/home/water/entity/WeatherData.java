package com.home.water.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (WeatherData)实体类
 *
 * @author xu.dm
 * @since 2020-03-30 21:53:07
 */
@Data
public class WeatherData implements Serializable {
    private static final long serialVersionUID = -13746580675329174L;
    
    private Integer weatherYear;
    
    private Integer temperature;
    
    private Integer id;

}