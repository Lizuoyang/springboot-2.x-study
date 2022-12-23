package com.lizuoyang.springboot.message.impl;

import com.lizuoyang.springboot.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SendToUserRequest
 * @Description 转发给对应人消息请求
 * @Author LiZuoYang
 * @Date 2021/3/11 15:05
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendToUserRequest implements Message {
    public static final String TYPE = "SEND_TO_USER_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;
}
