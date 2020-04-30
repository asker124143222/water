package com.home.water.service;

import java.util.concurrent.ExecutionException;

/**
 * @Author: xu.dm
 * @Date: 2020/4/30 15:59
 * @Version: 1.0
 * @Description: 限制登录次数
 **/
public interface LoginAttemptService {
    void loginSucessed(String ip);

    void loginFailed(String ip);

    boolean isBlocked(String ip);
}
