package com.home.water.service.impl;

import com.home.water.dao.UserDao;
import com.home.water.entity.User;
import com.home.water.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author xu.dm
 * @since 2020-03-30 22:41:44
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getOne(Integer id) {
        return userDao.getOne(id);
    }

    @Override
    public User getOneByNameAndPassword(User user) {
        return userDao.getOneByNameAndPassword(user);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int delete(Integer id) {
        return 0;
    }
}