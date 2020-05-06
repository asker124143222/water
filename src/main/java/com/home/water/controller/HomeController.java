package com.home.water.controller;

import com.home.water.bean.King;
import com.home.water.config.CustomParams;
import com.home.water.entity.UserInfo;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: xu.dm
 * @Date: 2020/3/27 22:26
 * @Description:
 */
@Controller
@Log4j2
public class HomeController {
    @Resource
    CustomParams customParams;

    @Resource
    King king;

    @Resource
    private ApplicationContext applicationContext;

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/show")
    public String getUser() {
        return "userinfo";
    }

    @PreAuthorize("view_king")
    @ResponseBody
    @GetMapping("/king")
    public Object getUserInfo() {
        log.info(king.toString());
        return king;
    }

    @ResponseBody
    @GetMapping("/userInfo")
    public Object getConfigInfo() {
        System.out.println("class:" + applicationContext.getClass() + "," + applicationContext.toString());
        if(customParams.isUserInfo()){
            UserInfo obj = (UserInfo)applicationContext.getBean("userInfo");
            log.info(obj.toString());
            return obj;

        }else return customParams;

    }

    @ResponseBody
    @GetMapping("/auth")
    public String getAuthenticationInfo(Authentication authentication){
        log.info("authentication:{}",authentication.toString());
        log.info("authentication.getName():{}",authentication.getName());
        log.info("authentication.getCredentials():{}",authentication.getCredentials());
        log.info("authentication.getAuthorities():{}",(authentication.getAuthorities()));
        log.info("authentication.getDetails():{}",authentication.getDetails());
        log.info("authentication.isAuthenticated():{}",authentication.isAuthenticated());
        return authentication.getName();
    }
}
