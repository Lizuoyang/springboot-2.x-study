package com.lizuoyang.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizuoyang.springboot.entity.UsersDO;
import com.lizuoyang.springboot.mapper.UsersMapper;
import com.lizuoyang.springboot.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2022-01-04
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersDO> implements UsersService {

}
