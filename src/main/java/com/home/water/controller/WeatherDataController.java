package com.home.water.controller;

import com.home.water.entity.WeatherData;
import com.home.water.model.WeatherUser;
import com.home.water.service.WeatherDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (WeatherData)表控制层
 *
 * @author xu.dm
 * @since 2020-03-31 12:26:19
 */
@RestController
@RequestMapping("weatherData")
public class WeatherDataController {
    /**
     * 服务对象
     */
    @Resource
    private WeatherDataService weatherDataService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public WeatherData selectOne(@PathVariable Long id) {
        return this.weatherDataService.queryById(id);
    }

    @GetMapping
    public List<WeatherData> queryAll(){
        return this.weatherDataService.queryAllByLimit(0,10);
    }

    /**
     * 新增数据
     *
     * @param weatherData 实体对象
     * @return 新增结果
     */
    @PostMapping
    public WeatherData insert(@RequestBody WeatherData weatherData) {
        return  weatherDataService.insert(weatherData);
    }

    @PutMapping
    public WeatherData update(@RequestBody WeatherData weatherData){
        return weatherDataService.update(weatherData);
    }

    @DeleteMapping
    public int delete(@RequestParam("idList") List<Long> idList) {
        int count = 0;
        for (Long i : idList) {
            count += weatherDataService.deleteById(i);
        }
        return count;
    }

    @GetMapping("/weatherAndUser")
    public List<WeatherUser> getWeatherAndUser(){
        return weatherDataService.getWeatherAndUser();
    }


    @GetMapping("/weatherAndUser/{id}")
    public List<WeatherUser> getWeatherAndUserByID(@PathVariable Long id){
        return weatherDataService.getWeatherAndUserByID(id);
    }

    @GetMapping("/weatherByName/{userName}")
    public List<WeatherUser> getWeatherAndUserByUserName(@PathVariable String userName){
        return weatherDataService.getWeatherAndUserByUserName(userName);
    }
}