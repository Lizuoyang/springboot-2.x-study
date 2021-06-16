package com.lizuoyang.springboot.message.impl;

import com.lizuoyang.springboot.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName AutoResponse
 * @Description 用户认真响应
 * @Author LiZuoYang
 * @Date 2021/3/11 14:52
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse implements Message {

    public static final String TYPE = "AUTH_RESPONSE";

    /**
     * 响应状态码
     */
    private String code;

    /**
     * 响应消息提示
     */
    private String messages;
}
