package com.lizuoyang.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizuoyang.springboot.entity.OrdersDO;
import com.lizuoyang.springboot.mapper.OrdersMapper;
import com.lizuoyang.springboot.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-07
 */
@Slf4j
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, OrdersDO> implements OrdersService {
    @Override
    public OrdersDO listById(int id) {
        return baseMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveByDO(OrdersDO ordersDO) {
        // 先读取从库数据
        OrdersDO order = baseMapper.selectById(1);
        System.out.println("slave orders");
        System.out.println(order);


        // 插入一条数据
        baseMapper.insert(ordersDO);

        // 再读取数据会从主库读取，原因如下
        //在 Sharding-JDBC 中，读写分离约定：同一线程且同一数据库连接内，如有写入操作，
        // 以后的读操作均从主库读取，用于保证数据一致性
        order = baseMapper.selectById(1);
        System.out.println("master orders");
        System.out.println(order);
    }
}
