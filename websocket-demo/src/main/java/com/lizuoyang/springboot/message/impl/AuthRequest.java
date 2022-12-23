package com.lizuoyang.springboot.message.impl;

import com.lizuoyang.springboot.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName AutoRequest
 * @Description 用户认真请求
 * @Author LiZuoYang
 * @Date 2021/3/11 14:46
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest implements Message {
    public static final String TYPE = "AUTH_REQUEST";

    /**
     * 认真token
     */
    private String accessToken;
}
