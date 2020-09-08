package com.home.water;

import com.home.water.task.TestTask;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: xu.dm
 * @Date: 2020/9/8 19:26
 * @Description:
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuartzApplicationTests {

    @Resource
    private Scheduler scheduler;

    @Test
    void myTest1() throws SchedulerException {
        JobDetail testTask = JobBuilder.newJob(TestTask.class)
                .withIdentity("TestTask")
                .storeDurably()
                .build();
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule("0/2 * * * * ? *");
        CronTrigger testTaskTrigger = TriggerBuilder.newTrigger()
                .forJob(testTask)
                .withIdentity("TestTaskTrigger")
                .withSchedule(cronSchedule)
                .build();
        scheduler.scheduleJob(testTask, testTaskTrigger);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("reset cron...");

        TriggerKey triggerKey = TriggerKey.triggerKey("TestTaskTrigger");
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String cronString = "0/10 * * * * ? *";
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronString);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(triggerKey, trigger);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
