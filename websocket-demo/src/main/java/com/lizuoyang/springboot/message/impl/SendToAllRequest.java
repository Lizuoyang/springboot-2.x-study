package com.lizuoyang.springboot.message.impl;

import com.lizuoyang.springboot.message.Message;
import lombok.Data;

/**
 * @ClassName SendToAllRequest
 * @Description 发送给所有人的消息请求
 * @Author LiZuoYang
 * @Date 2021/3/11 14:59
 **/
@Data
public class SendToAllRequest implements Message {
    public static final String TYPE = "SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;

    /**
     * 消息内容
     */
    private String content;
}
