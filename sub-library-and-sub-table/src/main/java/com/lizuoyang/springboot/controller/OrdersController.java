package com.lizuoyang.springboot.controller;


import com.lizuoyang.springboot.entity.OrdersDO;
import com.lizuoyang.springboot.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-07
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/get")
    public OrdersDO getOrder(@RequestParam("id") Integer orderId) {
        OrdersDO byId = ordersService.getById(orderId);
        return byId;
    }
}
