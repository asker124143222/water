package com.home.water.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: xu.dm
 * @Date: 2020/4/2 15:45
 * @Version: 1.0
 * @Description: ehecache缓存监听器
 **/
public class UserCacheListener implements CacheEventListener {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
        logger.info("cache element removed, name:{},key:{}",cache.getName(),element.getObjectKey());
    }

    @Override
    public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
        String name = cache.getName();
        Object key = element.getObjectKey();
        Object value = element.getObjectValue();
        logger.info("cache element put,name:{},key:{}",name,key);
    }

    @Override
    public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
        logger.info("缓存更新,name:{},key:{}",cache.getName(),element.getObjectKey());
    }

    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {
        logger.info("cache element expired,name:{},key:{}",cache.getName(),element.getObjectKey());
    }

    @Override
    public void notifyElementEvicted(Ehcache cache, Element element) {
        logger.info("cache element Evicted,name:{},key:{}",cache.getName(),element.getObjectKey());
    }

    @Override
    public void notifyRemoveAll(Ehcache cache) {
        logger.info("cache RemoveAll,name:{}",cache.getName());
    }

    @Override
    public void dispose() {

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return null;
    }
}
