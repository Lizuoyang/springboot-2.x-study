package com.lizuoyang.springboot.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @ClassName BaseJob
 * @Description 任务类基础接口
 * @Author LiZuoYang
 * @Date 2021/3/20 13:03
 **/
public interface BaseJob extends Job {
    /**
     * @author lizuoyang
     * @description 定时任务执行方法
     * @date 13:05 2021/3/20
     * @param context
     * @return void
     **/
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;
}
