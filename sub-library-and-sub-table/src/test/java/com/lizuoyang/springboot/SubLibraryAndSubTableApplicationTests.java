package com.lizuoyang.springboot;

import com.lizuoyang.springboot.entity.OrderConfigDO;
import com.lizuoyang.springboot.entity.OrdersDO;
import com.lizuoyang.springboot.mapper.OrderConfigMapper;
import com.lizuoyang.springboot.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SubLibraryAndSubTableApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private OrderConfigMapper orderConfigMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Test
    void test01() {
        OrderConfigDO orderConfigDO = orderConfigMapper.selectById(1);
        System.out.println(orderConfigDO);
    }

    @Test
    void test02() {
        OrdersDO ordersDO = ordersMapper.selectById(1);
        System.out.println(ordersDO);
    }

    @Test
    void test03() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", 1);
        List<OrdersDO> ordersDOS = ordersMapper.selectByMap(params);
        System.out.println(ordersDOS.get(0));
    }

    @Test
    void test04() {
        OrdersDO ordersDO = new OrdersDO();
        ordersDO.setUserId(1);
        ordersDO.setId(1);
        ordersMapper.insert(ordersDO);
    }

}
