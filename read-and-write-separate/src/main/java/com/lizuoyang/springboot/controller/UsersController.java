package com.lizuoyang.springboot.controller;


import com.lizuoyang.springboot.entity.UsersDO;
import com.lizuoyang.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-07
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/get")
    public UsersDO getUser(@RequestParam("id") Integer id) {
        UsersDO byId = usersService.getById(id);
        return byId;
    }

}
