package com.home.water.service.impl;

import com.home.water.entity.WeatherData;
import com.home.water.dao.WeatherDataDao;
import com.home.water.model.UserWeather;
import com.home.water.model.WeatherUser;
import com.home.water.service.WeatherDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WeatherData)表服务实现类
 *
 * @author xu.dm
 * @since 2020-03-31 12:26:19
 */
@Service("weatherDataService")
public class WeatherDataServiceImpl implements WeatherDataService {
    @Resource
    private WeatherDataDao weatherDataDao;

    @Override
    public List<WeatherData> queryAll(WeatherData weatherData) {
        return weatherDataDao.queryAll(weatherData);
    }

    @Override
    public List<WeatherUser> getWeatherAndUser() {
        return weatherDataDao.getWeatherAndUser();
    }

    @Override
    public List<WeatherUser> getWeatherAndUserByID(Long id) {
        return weatherDataDao.getWeatherAndUserByID(id);
    }

    @Override
    public List<WeatherUser> getWeatherAndUserByUserName(String userName) {
        return weatherDataDao.getWeatherAndUserByUserName(userName);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public WeatherData queryById(Long id) {
        return this.weatherDataDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<WeatherData> queryAllByLimit(int offset, int limit) {
        return this.weatherDataDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param weatherData 实例对象
     * @return 实例对象
     */
    @Override
    public WeatherData insert(WeatherData weatherData) {
        this.weatherDataDao.insert(weatherData);
        return weatherData;
    }

    /**
     * 修改数据
     *
     * @param weatherData 实例对象
     * @return 实例对象
     */
    @Override
    public WeatherData update(WeatherData weatherData) {
        this.weatherDataDao.update(weatherData);
        return this.queryById(weatherData.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(Long id) {
        return this.weatherDataDao.deleteById(id);
    }
}