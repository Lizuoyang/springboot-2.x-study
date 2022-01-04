package com.lizuoyang.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserCacheDO
 * @Description Redis 序列化类
 * @Author LiZuoYang
 * @Date 2021/12/21 16:03
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCacheDO {

    private Integer id;
    private String name;
    private Integer gender;
}
