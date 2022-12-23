package com.lizuoyang.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description 用户测试接口
 * @Author LiZuoYang
 * @Date 2021/3/20 10:25
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{userid}")
    public String queryUserDetails(@PathVariable String userid) {

        return userid;
    }
}
