package com.home.water.task;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xu.dm
 * @Date: 2020/9/8 19:34
 * @Description:
 */
//@Configuration
public class QuartConfig {

//    @Bean
    public JobDetail testTaskJob(){
        return JobBuilder.newJob(TestTask.class)
                .withIdentity("TestTask")
                .storeDurably()
                .build();
    }

//    @Bean
    public Trigger testTaskTrigger(){
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/2 * * * * ? *");
        return TriggerBuilder.newTrigger()
                .forJob(testTaskJob())
                .withIdentity("TestTask")
                .withSchedule(cronSchedule)
                .build();
    }
}
