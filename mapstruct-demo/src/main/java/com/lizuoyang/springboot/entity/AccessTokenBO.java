package com.lizuoyang.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName AccessTokenBO
 * @Description 用户token业务类
 * @Author LiZuoYang
 * @Date 2021/3/1 14:04
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AccessTokenBO {
    /**
     * token
     */
    private String accessToken;
    /**
     * 超时时间
     */
    private String expiresTime;
}
