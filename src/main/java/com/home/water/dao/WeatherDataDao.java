package com.home.water.dao;

import com.home.water.entity.WeatherData;
import com.home.water.model.WeatherUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (WeatherData)表数据库访问层
 *
 * @author xu.dm
 * @since 2020-03-31 12:26:19
 */
public interface WeatherDataDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    WeatherData queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<WeatherData> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param weatherData 实例对象
     * @return 对象列表
     */
    List<WeatherData> queryAll(WeatherData weatherData);

    /**
     * 新增数据
     *
     * @param weatherData 实例对象
     * @return 影响行数
     */
    int insert(WeatherData weatherData);

    /**
     * 修改数据
     *
     * @param weatherData 实例对象
     * @return 影响行数
     */
    int update(WeatherData weatherData);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<WeatherUser> getWeatherAndUser();

    List<WeatherUser> getWeatherAndUserByID(Long id);

    @Select("select u.`userId`,u.`userName`,wd.weather_year,wd.avg_value " +
            "from `user` u inner join weather_data wd  " +
            "on u.`userId`= wd.userid " +
            "where u.`userName`=#{userName};")
    List<WeatherUser> getWeatherAndUserByUserName(String userName);
}