package com.home.water.security;

import com.home.water.entity.User;
import com.home.water.service.LoginAttemptService;
import com.home.water.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xu.dm
 * @Date: 2020/4/30 13:29
 * @Version: 1.0
 * @Description:
 * 1、自定义用户和密码比对
 * 2、ip黑名单
 **/
@Component
@Slf4j
public class CustomUserNamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private LoginAttemptService loginAttemptService;

    @Resource
    private UserService userService;

    private List<String> blackIps = new ArrayList<>();

    public CustomUserNamePasswordAuthenticationProvider() {
        //硬编码，实际根据需求从数据库中加载
//        blackIps.add("192.168.1.104");
        blackIps.add("192.168.1.100");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails)authentication.getDetails();
        String ip = details.getRemoteAddress();

        //黑名单判断
        if(blackIps.contains(ip)){
            log.info("非法IP地址：{}",ip);
            throw new BadCredentialsException("无效的IP地址");
        }

        //超过密码尝试次数后，IP地址被BLOCKED
        if(loginAttemptService.isBlocked(ip)){
            log.info("IP地址：{} 已经被Blocked",ip);
            throw new BadCredentialsException("IP地址"+ip+"已经被Blocked");
        }

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.getUserByName(username);
        if(user ==null){
            throw new BadCredentialsException("无效的用户:"+username);
        }

        boolean passed = BCrypt.checkpw(password, user.getPassword());
        if(username.equals(user.getUsername()) && passed){
            //这里注意不要调错函数
            log.info("用户登录：{}",username);
            return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
        }else {
            throw new BadCredentialsException("无效的用户/密码");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
