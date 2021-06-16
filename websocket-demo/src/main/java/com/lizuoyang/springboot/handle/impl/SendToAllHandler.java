package com.lizuoyang.springboot.handle.impl;

import com.lizuoyang.springboot.handle.MessageHandler;
import com.lizuoyang.springboot.message.impl.SendResponse;
import com.lizuoyang.springboot.message.impl.SendToAllRequest;
import com.lizuoyang.springboot.message.impl.SendToOneRequest;
import com.lizuoyang.springboot.message.impl.SendToUserRequest;
import com.lizuoyang.springboot.util.WebSocketUtil;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * @ClassName SendToAllHandler
 * @Description 发送所有人消息处理器
 * @Author LiZuoYang
 * @Date 2021/3/11 15:34
 **/
@Component
public class SendToAllHandler implements MessageHandler<SendToAllRequest> {
    @Override
    public void execute(Session session, SendToAllRequest message) {
// 模拟直接成功
        SendResponse sendResponse = SendResponse.builder().code("0").msgId(message.getMsgId()).build();
        WebSocketUtil.send(session, SendToOneRequest.TYPE, sendResponse);

        // 创建转发的消息
        SendToUserRequest userRequest = SendToUserRequest.builder().content(message.getContent()).msgId(message.getMsgId()).build();
        // 广播发送
        WebSocketUtil.broadcast(SendToAllRequest.TYPE, userRequest);
    }

    @Override
    public String getType() {
        return SendToAllRequest.TYPE;
    }
}
