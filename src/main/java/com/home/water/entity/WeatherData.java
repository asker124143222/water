package com.home.water.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (WeatherData)实体类
 *
 * @author xu.dm
 * @since 2020-03-31 12:26:19
 */
@Data
public class WeatherData implements Serializable {
    private static final long serialVersionUID = -34431012340770708L;
    
    private Long id;
    
    private Integer weatherYear;
    
    private Integer avgValue;

    private Integer userid;

}