package com.home.water.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Author: xu.dm
 * @Date: 2020/3/31 17:10
 * @Version: 1.0
 * @Description: TODO
 **/
@Component
@Aspect
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.home.water.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LocalDateTime.now().toString() + " 方法执行前:");
        stringBuilder.append(",url:" + request.getRequestURI());
        stringBuilder.append(",ip:" + request.getRemoteAddr());
        stringBuilder.append(",method:" + request.getMethod());
        stringBuilder.append(",class_method:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        stringBuilder.append(",args:" + joinPoint.getArgs());

        logger.info(stringBuilder.toString());
    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        String msg = LocalDateTime.now().toString() + " 方法执行完成" + ",class：" + joinPoint.getTarget().getClass().getName()
                + ",method: " + joinPoint.getSignature().getName() + ",args: " + Arrays.toString(joinPoint.getArgs());

        logger.info(msg);
    }

    @AfterReturning(pointcut = "log()", returning = "result")
    public void doAfterReturning(Object result) {
        String msg = "方法返回:" + result;
        logger.info(msg);
    }
}
