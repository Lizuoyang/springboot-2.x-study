package com.lizuoyang.springboot.handle.impl;

import com.lizuoyang.springboot.handle.MessageHandler;
import com.lizuoyang.springboot.message.impl.AuthRequest;
import com.lizuoyang.springboot.message.impl.AuthResponse;
import com.lizuoyang.springboot.message.impl.UserJoinNoticeRequest;
import com.lizuoyang.springboot.util.WebSocketUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.Session;

/**
 * @ClassName AuthMessageHandler
 * @Description 用户认真消息处理器
 * @Author LiZuoYang
 * @Date 2021/3/11 15:10
 **/
@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest> {
    @Override
    public void execute(Session session, AuthRequest message) {
        // 未传入token 返回消息提示
        if (StringUtils.isEmpty(message.getAccessToken())) {
            WebSocketUtil.send(session, AuthRequest.TYPE, AuthResponse.builder().code("1").messages("认证 accessToken 未传入").build());
            return;
        }

        // 添加session
        WebSocketUtil.addSession(session, message.getAccessToken());

        // 判断是否认证成功， 模拟直接当判断成功
        WebSocketUtil.send(session, AuthRequest.TYPE, AuthResponse.builder().code("0").build());

        // 通知所有人，某人加入
        WebSocketUtil.broadcast(UserJoinNoticeRequest.TYPE, UserJoinNoticeRequest.builder().nickName(message.getAccessToken()).build());

    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}
