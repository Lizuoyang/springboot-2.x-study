package com.lizuoyang.springboot.service.impl;

import com.lizuoyang.springboot.entity.UsersDO;
import com.lizuoyang.springboot.mapper.UsersMapper;
import com.lizuoyang.springboot.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-07
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersDO> implements UsersService {

}
