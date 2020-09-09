package com.home.water;

import com.home.water.entity.User;
import com.home.water.service.UserService;
import com.home.water.task.TestTask;
import com.home.water.task.TestTask2;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: xu.dm
 * @Date: 2020/9/8 19:26
 * @Description:
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuartzApplicationTest2 {

    private String job_key = "jobKey";
    private String prefix_job_name = "task_";

    @Resource
    private Scheduler scheduler;

    @Resource
    private UserService userService;

    @Test
    void myTest1() throws SchedulerException {
        User user = userService.getOne(1);
        JobDetail testTask = JobBuilder.newJob(TestTask2.class)
                .withIdentity(prefix_job_name+user.getUserid())
                .storeDurably()
                .build();
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");
        CronTrigger testTaskTrigger = TriggerBuilder.newTrigger()
//                .startNow()
//                .forJob(testTask)
                .withIdentity(prefix_job_name+user.getUserid())
                .withSchedule(cronSchedule)
                .build();
        testTaskTrigger.getJobDataMap().put(job_key,user);
        scheduler.scheduleJob(testTask, testTaskTrigger);


        user = userService.getOne(3);
        testTask = JobBuilder.newJob(TestTask2.class)
                .withIdentity(prefix_job_name+user.getUserid())
                .storeDurably()
                .build();
        cronSchedule = CronScheduleBuilder.cronSchedule("0/10 * * * * ? *");
        testTaskTrigger = TriggerBuilder.newTrigger()
//                .startNow()
//                .forJob(testTask)
                .withIdentity(prefix_job_name+user.getUserid())
                .withSchedule(cronSchedule)
                .build();
        testTaskTrigger.getJobDataMap().put(job_key,user);
        scheduler.scheduleJob(testTask, testTaskTrigger);


        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
