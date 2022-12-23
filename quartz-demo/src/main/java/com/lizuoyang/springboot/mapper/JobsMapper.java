package com.lizuoyang.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizuoyang.springboot.entity.JobAndTrigger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName JobsMapper
 * @Description JobsMapper
 * @Author LiZuoYang
 * @Date 2021/3/20 13:09
 **/
@Mapper
public interface JobsMapper extends BaseMapper<JobAndTrigger> {
    List<JobAndTrigger> selectListByPage();
}
