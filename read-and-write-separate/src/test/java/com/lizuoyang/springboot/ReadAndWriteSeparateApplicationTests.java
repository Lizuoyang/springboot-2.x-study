package com.lizuoyang.springboot;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lizuoyang.springboot.entity.OrdersDO;
import com.lizuoyang.springboot.mapper.OrdersMapper;
import com.lizuoyang.springboot.service.OrdersService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReadAndWriteSeparateApplicationTests {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersMapper ordersMapper;

    @Test
    void contextLoads() {
    }

    /**
     * 测试从库的负载均衡
     */
    @Test
    void test01() {
        for (int i = 0; i < 10; i++) {
            OrdersDO ordersDO = ordersService.listById(1);
            System.out.println(ordersDO);
        }
    }

    /**
     * 测试强制访问主库
     */
    @Test
    void test02() {
        try (HintManager hintManager = HintManager.getInstance()) {
            // 设置强制访问主库
            hintManager.setMasterRouteOnly();
            OrdersDO ordersDO = ordersService.listById(1);
            System.out.println(ordersDO);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void testInsert() {
        OrdersDO ordersDO = new OrdersDO();
        ordersDO.setId((int)IdWorker.getId());
        ordersDO.setUserId(100);
        ordersMapper.insert(ordersDO);
    }

    @Test
    void testReadAndWrite() {
        // 再插入一条数据到主库
        OrdersDO ordersDO = new OrdersDO();
        ordersDO.setId((int) IdWorker.getId());
        ordersDO.setUserId(200);

        ordersService.saveByDO(ordersDO);
    }

}
