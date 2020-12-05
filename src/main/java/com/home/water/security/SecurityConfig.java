package com.home.water.security;

import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;

/**
 * @Author: xu.dm
 * @Date: 2020/4/30 11:30
 * @Version: 1.0
 * @Description: TODO
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private CorsFilter corsFilter;

    @Resource
    private CustomUserNamePasswordAuthenticationProvider customUserNamePasswordAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

//                .headers().frameOptions().disable()
//                .and()
                .authorizeRequests()
                .antMatchers("/login.html","logout.html","/websocket.html","/login","/logout").permitAll()
                .antMatchers("/ureport/**").permitAll()
                // 静态资源等等
                .antMatchers(
                        HttpMethod.GET,
                        "/static/**",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.ttf",
                        "/**/*.woff",
                        "/**/*.map",
                        "/webSocket/**",
                        "favicon.ico "
                ).permitAll()
                .antMatchers("/oAuth/login").permitAll()
                .antMatchers("/druid").permitAll()
                .anyRequest()
                .authenticated()


                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")
                .permitAll();

        http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //是否擦除密码，生产环境应该要设置为true（默认true）
//        auth.eraseCredentials(false);
        //使用自定义的密码验证器
        auth.authenticationProvider(customUserNamePasswordAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
