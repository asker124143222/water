package com.home.water.security;

import com.home.water.service.LoginAttemptService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: xu.dm
 * @Date: 2020/4/30 16:43
 * @Version: 1.0
 * @Description: 登录成功的监听类
 **/
@Component
public class AuthenticationLoginSucessedEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Resource
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        WebAuthenticationDetails details = (WebAuthenticationDetails)event.getAuthentication().getDetails();
        loginAttemptService.loginSucessed(details.getRemoteAddress());
    }
}
