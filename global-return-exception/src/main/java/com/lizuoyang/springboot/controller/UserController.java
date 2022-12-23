package com.lizuoyang.springboot.controller;

import com.lizuoyang.springboot.exception.ServiceException;
import com.lizuoyang.springboot.exception.ServiceExceptionEnum;
import com.lizuoyang.springboot.message.ApiResponse;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Service;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName UserController
 * @Description 用户控制器
 * @Author LiZuoYang
 * @Date 2021/3/2 11:30
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")
    public ApiResponse getUserName() {
        return ApiResponse.ofSuccess("lizuoyang");
    }

    @GetMapping("/list")
    public List<String> getUsers() {
        List<String> list = Arrays.asList("lizuoyang", "lijiali");
        return list;
    }

    @GetMapping("/exception-01")
    public ApiResponse exception01() {
        throw new NullPointerException("空指针了");
    }

    @GetMapping("/exception-02")
    public ApiResponse exception02() {
        throw new ServiceException(ServiceExceptionEnum.USER_NOT_FOUND);
    }
}
