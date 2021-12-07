package com.lizuoyang.springboot.service.impl;

import com.lizuoyang.springboot.entity.OrdersDO;
import com.lizuoyang.springboot.mapper.OrdersMapper;
import com.lizuoyang.springboot.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-07
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, OrdersDO> implements OrdersService {

}
