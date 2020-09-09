package com.home.water.task;

import com.home.water.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.UUID;

/**
 * @Author: xu.dm
 * @Date: 2020/9/8 19:28
 * @Description:
 */
@Slf4j
@DisallowConcurrentExecution
public class TestTask2 extends QuartzJobBean {
    private String job_key = "jobKey";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        User user = (User) context.getMergedJobDataMap().get(job_key);
        log.info("task start ... userId: "+user.getUserid());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        log.info("task end ... userId: "+user.getUserid());
    }
}
