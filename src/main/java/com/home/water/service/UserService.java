package com.home.water.service;

import com.home.water.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author xu.dm
 * @since 2020-03-30 22:41:44
 */
public interface UserService {
    List<User> getAll();

    User getOne(Integer id);

    User getOneByNameAndPassword(User user);

    int insert(User user);

    int update(User user);

    int delete(Integer id);
}