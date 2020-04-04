package com.home.water.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.home.water.dao.UserDao;
import com.home.water.entity.User;
import com.home.water.model.UserVO;
import com.home.water.model.UserWeather;
import com.home.water.service.UserService;
import net.sf.ehcache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
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
@CacheConfig(cacheNames = "userCache")
public class UserServiceImpl implements UserService {
    private String salt = "8d78869f470951332959580424d4bf4f";

    @Resource
    UserDao userDao;

    @Cacheable(key = "'all_user'")
    @Override
    public List<UserVO> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<UserVO> queryAllByLimit(int offset, int limit) {
        return userDao.queryAllByLimit(offset,limit);
    }

    @Cacheable(key = "#root.methodName+'_pageNum_'+#pageNum+'_pageSize_'+#pageSize")
    @Override
    public PageInfo<UserVO> queryByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserVO> lists = userDao.getAll();
        PageInfo<UserVO> pages = new PageInfo<>(lists);
        return pages;
    }


    @Cacheable(key = "'user_' + #id")
    @Override
    public User getOne(Integer id) {
        return userDao.getOne(id);
    }

    @Cacheable(key = "#user.username + #user.password")
    @Override
    public User getOneByNameAndPassword(User user) {
        return userDao.getOneByNameAndPassword(user);
    }

    @CacheEvict(allEntries = true)
    @Override
    public int insert(User user) {
        user.setSalt(this.salt+user.getUsername());
        return userDao.insert(user);
    }

    @CacheEvict(allEntries = true)
    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @CacheEvict(allEntries = true)
    @Override
    public int delete(Integer id) {
        return userDao.delete(id);
    }

    @Cacheable(key = "'all_UserAndWeather'")
    @Override
    public List<UserWeather> getAllUserAndWeather() {
        return userDao.getAllUserAndWeather();
    }

    @Cacheable(key = "'UserAndWeather_' + #id")
    @Override
    public List<UserWeather> getUserAndWeatherByID(Integer id) {
        return userDao.getUserAndWeatherByID(id);
    }
}