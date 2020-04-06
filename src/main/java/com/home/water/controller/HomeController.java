package com.home.water.controller;

import com.home.water.bean.King;
import com.home.water.config.CustomParams;
import com.home.water.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Controller;
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
public class HomeController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    CustomParams customParams;

    @Resource
    King king;

    @Resource
    private ApplicationContext applicationContext;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/king")
    public Object getUserInfo() {
        logger.info(king.toString());
        return king;
    }

    @ResponseBody
    @RequestMapping("/userInfo")
    public Object getConfigInfo() {
        System.out.println("class:" + applicationContext.getClass() + "," + applicationContext.toString());
        if(customParams.isUserInfo()){
            UserInfo obj = (UserInfo)applicationContext.getBean("userInfo");
            logger.info(obj.toString());
            return obj;

        }else return customParams;

    }
}
