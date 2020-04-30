package com.home.water.security;

import com.home.water.service.LoginAttemptService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: xu.dm
 * @Date: 2020/4/30 16:47
 * @Version: 1.0
 * @Description: 登录失败的监听类
 **/
@Component
public class AuthenticationLoginFailedEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Resource
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        WebAuthenticationDetails details = (WebAuthenticationDetails)event.getAuthentication().getDetails();
        loginAttemptService.loginFailed(details.getRemoteAddress());
    }
}
