package com.lizuoyang.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.lizuoyang.springboot.entity.JobAndTrigger;

/**
 * @author lizuoyang
 * @description JobsServce
 * @date 13:09 2021/3/20
 **/
public interface JobsServce extends IService<JobAndTrigger> {
    PageInfo<JobAndTrigger> listJobsPage(int pageNum, int pageSize);
}
