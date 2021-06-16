package com.lizuoyang.springboot.message.impl;

import com.lizuoyang.springboot.message.Message;
import lombok.Data;

/**
 * @ClassName SendToOneRequest
 * @Description 发送给指定人的私聊消息请求
 * @Author LiZuoYang
 * @Date 2021/3/11 14:58
 **/
@Data
public class SendToOneRequest implements Message {
    public static final String TYPE = "SEND_TO_ONE_REQUEST";

    /**
     * 发送给的用户
     */
    private String toUser;
    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;
}
