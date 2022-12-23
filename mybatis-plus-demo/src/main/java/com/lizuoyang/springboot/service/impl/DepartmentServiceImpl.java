package com.lizuoyang.springboot.service.impl;

import com.lizuoyang.springboot.entity.DepartmentDO;
import com.lizuoyang.springboot.mapper.DepartmentMapper;
import com.lizuoyang.springboot.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-02
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, DepartmentDO> implements DepartmentService {

}
