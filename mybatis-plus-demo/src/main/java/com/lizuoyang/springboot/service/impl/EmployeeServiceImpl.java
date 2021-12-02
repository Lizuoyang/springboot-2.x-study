package com.lizuoyang.springboot.service.impl;

import com.lizuoyang.springboot.entity.EmployeeDO;
import com.lizuoyang.springboot.mapper.EmployeeMapper;
import com.lizuoyang.springboot.service.EmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, EmployeeDO> implements EmployeeService {

}
