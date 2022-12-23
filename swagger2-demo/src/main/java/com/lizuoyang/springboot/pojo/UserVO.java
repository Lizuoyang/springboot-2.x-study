package com.lizuoyang.springboot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserVO
 * @Description 用户VO
 * @Author LiZuoYang
 * @Date 2021/3/9 16:36
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户 VO")
public class UserVO {
    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    private Integer id;
    @ApiModelProperty(value = "账号", required = true, example = "lizuoyang")
    private String username;
}
