package com.lizuoyang.springboot.service;

import com.lizuoyang.springboot.pojo.UserAddDTO;
import com.lizuoyang.springboot.pojo.UserUpdateGenderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author LiZuoYang
 * @Date 2021/3/10 15:37
 **/
@Validated
@Slf4j
@Service
public class UserService {
    public void get(@Min(value = 1L, message = "编号必须大于 0") Integer id) {
        log.info("[get][id: {}]", id);
    }

    public void add(@Valid UserAddDTO addDTO) {
        log.info("[add][addDTO: {}]", addDTO);
    }

    public void add01(UserAddDTO addDTO) {
        this.add(addDTO);
    }

    public void add02(UserAddDTO addDTO) {
        self().add(addDTO);
    }

    public void updateGender(@Valid UserUpdateGenderDTO genderDTO) {
        log.info("[updateGender][genderDTO: {}]", genderDTO);
    }

    private UserService self() {
        return (UserService) AopContext.currentProxy();
    }

}
