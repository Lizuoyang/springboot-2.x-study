package com.lizuoyang.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lizuoyang.springboot.entity.JobAndTrigger;
import com.lizuoyang.springboot.mapper.JobsMapper;
import com.lizuoyang.springboot.service.JobsServce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName JobServiceImpl
 * @Description JobServiceImpl
 * @Author LiZuoYang
 * @Date 2021/3/20 13:10
 **/
@Service
public class JobServiceImpl extends ServiceImpl<JobsMapper, JobAndTrigger> implements JobsServce {
    @Autowired
    private JobsMapper jobsMapper;

    @Override
    public PageInfo<JobAndTrigger> listJobsPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobsMapper.selectListByPage();
        PageInfo<JobAndTrigger> page = new PageInfo<>(list);
        return page;
    }
}
