package com.home.water.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.home.water.entity.User;
import com.home.water.model.UserVO;
import com.home.water.model.UserWeather;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author xu.dm
 * @since 2020-03-30 22:41:44
 */
public interface UserService {
    List<UserVO> getAll();

    List<UserVO> queryAllByLimit(int offset, int limit);

    PageInfo<UserVO> queryByPage(int pageNum, int pageSize);

    User getOne(Integer id);

    User getOneByNameAndPassword(User user);

    int insert(User user);

    int update(User user);

    int delete(Integer id);

    List<UserWeather> getAllUserAndWeather();

    List<UserWeather> getUserAndWeatherByID(Integer id);
}