package com.lizuoyang.springboot.message.impl;

import com.lizuoyang.springboot.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SendResponse
 * @Description 发送消息响应
 * @Author LiZuoYang
 * @Date 2021/3/11 15:00
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendResponse implements Message {
    public static final String TYPE = "SEND_RESPONSE";

    /**
     * 消息编号
     */
    private String msgId;

    /**
     * 响应状态码
     */
    private String code;

    /**
     * 消息内容
     */
    private String message;

}
