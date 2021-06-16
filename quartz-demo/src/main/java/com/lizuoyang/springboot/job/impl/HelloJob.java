package com.lizuoyang.springboot.job.impl;

import com.lizuoyang.springboot.job.BaseJob;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * @ClassName HelloJob
 * @Description Job示例
 * @Author LiZuoYang
 * @Date 2021/3/20 13:06
 **/
@Slf4j
@NoArgsConstructor
public class HelloJob implements BaseJob {
    @Override
    public void execute(JobExecutionContext context) {
        log.info("Hello Job执行时间: " + new Date());
    }
}
