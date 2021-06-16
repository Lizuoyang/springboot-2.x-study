package com.lizuoyang.springboot.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName JobDTO
 * @Description 定时任务参数类
 * @Author LiZuoYang
 * @Date 2021/3/20 13:50
 **/
@Data
public class JobDTO implements Serializable {
    /**
     * 任务类名称
     */
    private String jobClassName;
    /**
     * 任务组名称
     */
    private String jobGroupName;
    /**
     * 表达式
     */
    private String cronExpression;
}
