package com.lizuoyang.springboot.entity;

import lombok.Data;

import java.math.BigInteger;

/**
 * @ClassName Jobs
 * @Description JobAndTrigger
 * @Author LiZuoYang
 * @Date 2021/3/20 12:54
 **/
@Data
public class JobAndTrigger {

    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String triggerGroup;
    private BigInteger repeatInterval;
    private BigInteger timesTriggered;
    private String cronExpression;
    private String timeZoneId;
}
