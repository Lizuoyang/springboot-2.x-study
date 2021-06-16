package com.lizuoyang.springboot.handle;

import com.lizuoyang.springboot.message.Message;

import javax.websocket.Session;

/**
 * @ClassName MessageHandle
 * @Description 消息处理器接口
 * @Author LiZuoYang
 * @Date 2021/3/11 15:08
 **/
public interface MessageHandler<T extends Message> {

    /**
     * 执行处理消息
     * @param session 会话
     * @param message 消息
     */
    void execute(Session session, T message);

    /**
     * @return 消息类型，即每个 Message 实现类上的 TYPE 静态字段
     */
    String getType();
}
