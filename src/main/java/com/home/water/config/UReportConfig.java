package com.home.water.config;

import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: xu.dm
 * @Date: 2020/10/14 16:44
 * @Version: 1.0
 * @Description: TODO
 **/
@ImportResource("classpath:ureport-console-context.xml")
@Configuration
@EnableAutoConfiguration
public class UReportConfig implements BuildinDatasource {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    DataSource dataSource;

    @Bean //定义ureport的启动servlet
    @SuppressWarnings("unchecked")
    public ServletRegistrationBean<UReportServlet> ureportServlet(){
        ServletRegistrationBean<UReportServlet> servletRegistrationBean = new ServletRegistrationBean<>(new UReportServlet());
        servletRegistrationBean.addUrlMappings("/ureport/*");
        return servletRegistrationBean;
    }

    @Override
    public String name() {
        return "water_db";
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error("UReport 数据源 获取连接失败！");
            e.printStackTrace();
        }
        return null;
    }
}
