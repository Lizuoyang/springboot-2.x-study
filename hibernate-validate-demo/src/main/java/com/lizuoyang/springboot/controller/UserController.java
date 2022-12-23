package com.lizuoyang.springboot.controller;

import com.lizuoyang.springboot.pojo.UserAddDTO;
import com.lizuoyang.springboot.pojo.UserUpdateGenderDTO;
import com.lizuoyang.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author LiZuoYang
 * @Date 2021/3/10 15:24
 **/
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public void get(@RequestParam("id") Integer id) {
        userService.get(id);
    }

    @PostMapping("/add")
    public void add(UserAddDTO addDTO) {
        userService.add(addDTO);
    }

    @PostMapping("/add01")
    public void add01(UserAddDTO addDTO) {
        userService.add01(addDTO);
    }

    @PostMapping("/add02")
    public void add02(UserAddDTO addDTO) {
        userService.add02(addDTO);
    }

    @PostMapping("/updateGenerate")
    public void updateGenerate(UserUpdateGenderDTO genderDTO) {
        userService.updateGender(genderDTO);
    }
}
