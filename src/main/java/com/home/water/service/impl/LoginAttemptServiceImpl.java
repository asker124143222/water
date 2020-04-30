package com.home.water.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.home.water.service.LoginAttemptService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: xu.dm
 * @Date: 2020/4/30 16:01
 * @Version: 1.0
 * @Description: TODO
 **/
@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {
    private static final Integer MAX_ATTEMPT=3;
    private LoadingCache<String,Integer> attemptCache;

    public LoginAttemptServiceImpl() {
        this.attemptCache = CacheBuilder.newBuilder()
                //缓存数据过期时间，1个小时候可以重新计数
                .expireAfterWrite(1, TimeUnit.HOURS)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 0;
                    }
                });
    }

    @Override
    public void loginSucessed(String ip) {
        attemptCache.invalidate(ip);
    }

    @Override
    public void loginFailed(String ip) {
        Integer attempts = 0;
        try {
            attempts = attemptCache.get(ip);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        attempts++;
        attemptCache.put(ip,attempts);

    }

    @Override
    public boolean isBlocked(String ip) {
        try {
            return attemptCache.get(ip) > MAX_ATTEMPT;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }
}
