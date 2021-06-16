package com.lizuoyang.springboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName UserUpdateDTO
 * @Description 用户修改DTO
 * @Author LiZuoYang
 * @Date 2021/3/9 16:35
 **/
@Data
@ApiModel("用户更新 DTO")
public class UserUpdateDTO {
    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    private Integer id;
    @ApiModelProperty(value = "账号", required = true, example = "lizuoyang")
    private String username;
    @ApiModelProperty(value = "密码", required = true, example = "11qqAA")
    private String password;
}
