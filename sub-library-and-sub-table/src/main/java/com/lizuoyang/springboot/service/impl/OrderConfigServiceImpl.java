package com.lizuoyang.springboot.service.impl;

import com.lizuoyang.springboot.entity.OrderConfigDO;
import com.lizuoyang.springboot.mapper.OrderConfigMapper;
import com.lizuoyang.springboot.service.OrderConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单配置表 服务实现类
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-13
 */
@Service
public class OrderConfigServiceImpl extends ServiceImpl<OrderConfigMapper, OrderConfigDO> implements OrderConfigService {

}
