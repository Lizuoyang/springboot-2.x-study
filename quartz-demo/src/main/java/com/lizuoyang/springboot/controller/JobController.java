package com.lizuoyang.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.lizuoyang.springboot.dto.JobDTO;
import com.lizuoyang.springboot.entity.JobAndTrigger;
import com.lizuoyang.springboot.job.BaseJob;
import com.lizuoyang.springboot.service.JobsServce;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName JobController
 * @Description JobController
 * @Author LiZuoYang
 * @Date 2021/3/20 13:08
 **/
@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {
    /**
     * 加入Qulifier注解，通过名称注入bean
     */
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @Autowired
    private JobsServce jobsServce;

    @GetMapping("/list")
    public PageInfo<JobAndTrigger> queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<JobAndTrigger> pageInfo = jobsServce.listJobsPage(pageNum, pageSize);
        return pageInfo;
    }

    @PostMapping("/save")
    public void addjob(@RequestBody JobDTO jobDTO) throws Exception {
        addJob(jobDTO.getJobClassName(), jobDTO.getJobGroupName(), jobDTO.getCronExpression());
    }

    public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            log.error("创建定时任务失败: {}", e);
            throw new Exception("创建定时任务失败");
        }
    }


    /**
     * @return void
     * @author lizuoyang
     * @description 暂停执行任务
     * @date 13:56 2021/3/20
     **/
    @PostMapping("/pause")
    public void pausejob(@RequestBody JobDTO dto) throws Exception {
        jobPause(dto.getJobClassName(), dto.getJobGroupName());
    }

    public void jobPause(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    /**
     * @param jobDTO
     * @return void
     * @author lizuoyang
     * @description 继续执行任务
     * @date 13:56 2021/3/20
     **/
    @PostMapping("/resume")
    public void resumejob(@RequestBody JobDTO jobDTO) throws Exception {
        jobresume(jobDTO.getJobClassName(), jobDTO.getJobGroupName());
    }

    public void jobresume(String jobClassName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    /**
     * @param jobDTO
     * @return void
     * @author lizuoyang
     * @description 重新构建任务
     * @date 13:57 2021/3/20
     **/
    @PostMapping("/reschedule")
    public void rescheduleJob(@RequestBody JobDTO jobDTO) throws Exception {
        jobreschedule(jobDTO.getJobClassName(), jobDTO.getJobGroupName(), jobDTO.getCronExpression());
    }

    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("更新定时任务失败: {}", e);
            throw new Exception("更新定时任务失败");
        }
    }


    /**
     * @param jobDTO
     * @return void
     * @author lizuoyang
     * @description 删除任务
     * @date 13:57 2021/3/20
     **/
    @PostMapping("/delete")
    public void deletejob(@RequestBody JobDTO jobDTO) throws Exception {
        jobdelete(jobDTO.getJobClassName(), jobDTO.getJobGroupName());
    }

    public void jobdelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }
}
