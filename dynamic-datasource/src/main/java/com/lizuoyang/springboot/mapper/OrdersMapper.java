package com.lizuoyang.springboot.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizuoyang.springboot.constants.DBConstants;
import com.lizuoyang.springboot.entity.OrdersDO;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-07
 */
@DS(DBConstants.DATASOURCE_ORDERS)
public interface OrdersMapper extends BaseMapper<OrdersDO> {

}
