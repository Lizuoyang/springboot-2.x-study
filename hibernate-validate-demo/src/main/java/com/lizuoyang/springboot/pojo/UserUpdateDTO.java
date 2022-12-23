package com.lizuoyang.springboot.pojo;

import lombok.Data;

/**
 * @ClassName UserUpdateDTO
 * @Description 用户修改DTO
 * @Author LiZuoYang
 * @Date 2021/3/9 16:35
 **/
@Data
public class UserUpdateDTO {
    private Integer id;
    private String username;
    private String password;
}
