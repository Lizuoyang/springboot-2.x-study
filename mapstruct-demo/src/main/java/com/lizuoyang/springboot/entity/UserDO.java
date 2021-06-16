package com.lizuoyang.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName UserDO
 * @Description 数据库实体类
 * @Author LiZuoYang
 * @Date 2021/3/1 11:10
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDO {
    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 用户姓名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
