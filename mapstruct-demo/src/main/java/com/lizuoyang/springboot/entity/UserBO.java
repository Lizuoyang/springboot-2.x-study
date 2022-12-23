package com.lizuoyang.springboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @ClassName UserBO
 * @Description 业务类
 * @Author LiZuoYang
 * @Date 2021/3/1 11:24
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserBO {
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
