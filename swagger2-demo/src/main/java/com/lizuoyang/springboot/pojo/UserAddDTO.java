package com.lizuoyang.springboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName 用户新增DTO
 * @Description 用户DTO
 * @Author LiZuoYang
 * @Date 2021/3/9 16:30
 **/
@Data
@ApiModel("用户新增 DTO")
public class UserAddDTO {
    @ApiModelProperty(value = "账号", required = true, example = "lizuoyang")
    private String username;
    @ApiModelProperty(value = "密码", required = true, example = "11qqAA")
    private String password;
}
