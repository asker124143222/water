package com.home.water.service;

import com.home.water.entity.WeatherData;
import com.home.water.model.WeatherUser;

import java.util.List;

/**
 * (WeatherData)表服务接口
 *
 * @author xu.dm
 * @since 2020-03-31 12:26:19
 */
public interface WeatherDataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WeatherData queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WeatherData> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param weatherData 实例对象
     * @return 实例对象
     */
    WeatherData insert(WeatherData weatherData);

    /**
     * 修改数据
     *
     * @param weatherData 实例对象
     * @return 实例对象
     */
    WeatherData update(WeatherData weatherData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Long id);

    List<WeatherData> queryAll(WeatherData weatherData);

    List<WeatherUser> getWeatherAndUser();

    List<WeatherUser> getWeatherAndUserByID(Long id);

}