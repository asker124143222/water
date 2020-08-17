package com.home.water.controller;

import com.home.water.bean.King;
import com.home.water.config.CustomParams;
import com.home.water.entity.UserInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;

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


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @GetMapping("/websocket")
    public String websocketTest(){
        return "websocket";
    }

    @GetMapping({"/","/index"})
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
        //Authentication 自动注入
        log.info("authentication:{}",authentication.toString());
        log.info("authentication.getName():{}",authentication.getName());
        log.info("authentication.getCredentials():{}",authentication.getCredentials());
        log.info("authentication.getAuthorities():{}",(authentication.getAuthorities()));
        log.info("authentication.getDetails():{}",authentication.getDetails());
        log.info("authentication.isAuthenticated():{}",authentication.isAuthenticated());
        return authentication.getName();
    }

    @ResponseBody
    @GetMapping("/auth2")
    public String getAuthenticationInfo2(){
        //通过上下文Holder获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication:{}",authentication.toString());
        log.info("authentication.getName():{}",authentication.getName());
        log.info("authentication.getCredentials():{}",authentication.getCredentials());
        log.info("authentication.getAuthorities():{}",(authentication.getAuthorities()));
        log.info("authentication.getDetails():{}",authentication.getDetails());
        log.info("authentication.isAuthenticated():{}",authentication.isAuthenticated());
        return authentication.getName();
    }

    @ResponseBody
    @GetMapping("/auth3")
    public String getAuthenticationInfo3(Principal principal){
        System.out.println(principal.toString());
        System.out.println(((UsernamePasswordAuthenticationToken) principal).getDetails());
        return principal.getName();
    }
}
