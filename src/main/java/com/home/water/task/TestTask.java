package com.home.water.task;

import com.home.water.model.UserVO;
import com.home.water.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: xu.dm
 * @Date: 2020/9/8 19:28
 * @Description:
 */
@Slf4j
@DisallowConcurrentExecution
public class TestTask extends QuartzJobBean {
    @Resource
    private UserService userService;


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        UUID uuid = UUID.randomUUID();
        log.info("task start ... Id: "+uuid.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        List<UserVO> list = userService.getAll();
//        log.info(list.toString());
//        log.info("task end ... Id: "+uuid.toString());
    }
}
