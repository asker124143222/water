package com.home.water.cache;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

import java.util.Properties;

/**
 * @Author: xu.dm
 * @Date: 2020/4/2 16:25
 * @Version: 1.0
 * @Description: ehecache缓存监听工厂类，类名需要配置在ehcache.xml里
 **/
public class UserCacheEventListenerFactory extends CacheEventListenerFactory {
    @Override
    public CacheEventListener createCacheEventListener(Properties properties) {
        return new UserCacheListener();
    }
}
