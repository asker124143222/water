package com.home.water.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: xu.dm
 * @Date: 2020/12/5 19:24
 * @Description:
 */
@RestController
public class OAuthController {

    @GetMapping("/oAuth/login")
    public void oAuthLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.setHeader("Authorization","Basic d29vZF93ZWI6MTIzNDU2");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600"); //设置过期时间
//        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
//        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
//        response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0");
        response.sendRedirect("http://localhost/oauth/authorize?response_type=code&client_id=wood_web");
//        response.sendRedirect("http://www.baidu.com");

    }
}
