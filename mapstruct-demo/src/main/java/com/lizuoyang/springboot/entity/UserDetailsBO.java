package com.lizuoyang.springboot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @ClassName UserDetailsBO
 * @Description 用户详情业务类
 * @Author LiZuoYang
 * @Date 2021/3/1 13:59
 **/
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserDetailsBO {
    /**
     * 用户编码
     */
    private String id;

    /**
     * token
     */
    private AccessTokenBO token;

}
