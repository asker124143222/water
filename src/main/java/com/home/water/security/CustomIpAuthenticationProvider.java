package com.home.water.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xu.dm
 * @Date: 2020/4/30 10:52
 * @Version: 1.0
 * @Description: 自定义IP黑白名单
 * todo AuthenticationProvider接口只能生效一个？ 这个功能移到 CustomUserNamePasswordAuthenticationProvider中了。
 **/

@Log4j2
@Component
public class CustomIpAuthenticationProvider implements AuthenticationProvider {

//    private List<String> whiteIps = new ArrayList<>();
    private List<String> blackIps = new ArrayList<>();

    public CustomIpAuthenticationProvider() {
        //硬编码，实际根据需求从数据库中加载
        blackIps.add("192.168.1.104");
        blackIps.add("192.168.1.100");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails)authentication.getDetails();
        String ip = details.getRemoteAddress();

        if(blackIps.contains(ip)){
            log.info("非法IP地址访问：{}",ip);
            throw new BadCredentialsException("无效的IP地址");
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
