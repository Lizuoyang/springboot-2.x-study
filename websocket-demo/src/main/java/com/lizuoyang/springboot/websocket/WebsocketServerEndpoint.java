package com.lizuoyang.springboot.websocket;

import com.alibaba.fastjson.JSONObject;
import com.lizuoyang.springboot.handle.MessageHandler;
import com.lizuoyang.springboot.message.Message;
import com.lizuoyang.springboot.message.impl.AuthRequest;
import com.lizuoyang.springboot.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName WebsocketServerEndpoint
 * @Description WebSocket 服务端端点
 * @Author LiZuoYang
 * @Date 2021/3/11 14:15
 **/
@Slf4j
@Controller
@ServerEndpoint("/")
public class WebsocketServerEndpoint implements InitializingBean {
    /**
     * 消息类型与 MessageHandler 的映射
     *
     * 注意，这里设置成静态变量。虽然说 WebsocketServerEndpoint 是单例，但是 Spring Boot 还是会为每个 WebSocket 创建一个 WebsocketServerEndpoint Bean 。
     */
    private static final Map<String, com.lizuoyang.springboot.handle.MessageHandler> HANDLERS = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    @OnOpen
    public void onOpen(Session session) {
        log.info("[onOpen][session({}) 接入]", session);
        // <1> 解析accessToken
        List<String> accessTokenValues = session.getRequestParameterMap().get("accessToken");
        String accessToken = !CollectionUtils.isEmpty(accessTokenValues) ? accessTokenValues.get(0) : null;
        // <2> 创建 AuthRequest 消息类型
        AuthRequest authRequest = AuthRequest.builder().accessToken(accessToken).build();
        // <3> 获得消息处理器
        MessageHandler messageHandler = HANDLERS.get(AuthRequest.TYPE);
        if (ObjectUtils.isEmpty(messageHandler)) {
            log.error("[onOpen][认证消息类型，不存在消息处理器]");
            return;
        }
        // <4> 处理消息
        messageHandler.execute(session, authRequest);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("[onOpen][session({}) 接收到一条消息({})]", session, message);

        try {
            // <1> 获得消息类型
            JSONObject messageJson = JSONObject.parseObject(message);
            String messageType = messageJson.getString("type");
            // <2> 获得消息处理器
            MessageHandler messageHandler = HANDLERS.get(messageType);
            if (messageHandler == null) {
                log.error("[onMessage][消息类型({}) 不存在消息处理器]", messageType);
                return;
            }
            // <3> 解析消息
            Class<? extends Message> messageClass = this.getMessageClass(messageHandler);
            Message messageBody = JSONObject.parseObject(messageJson.getString("body"), messageClass);
            // <4> 处理消息
            messageHandler.execute(session, messageBody);
        } catch (Exception exception) {
            log.info("[onMessage][session({}) message({}) 发生异常]", session, exception);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        log.info("[onClose][session({}) 连接关闭。关闭原因是({})}]", session, reason);
        WebSocketUtil.removeSession(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("[onClose][session({}) 发生异常]", session, throwable);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationContext.getBeansOfType(com.lizuoyang.springboot.handle.MessageHandler.class)
                .values()
                .forEach(hanler -> {
                    HANDLERS.put(hanler.getType(), hanler);
                });
        log.info("[afterPropertiesSet][消息处理器数量：{}]", HANDLERS.size());
    }

    private Class<? extends Message> getMessageClass(MessageHandler handler) {
        // 获得 Bean 对应的 Class 类名。因为有可能被 AOP 代理过。
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        // 获得接口的 Type 数组
        Type[] interfaces = targetClass.getGenericInterfaces();
        Class<?> superclass = targetClass.getSuperclass();
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)) { // 此处，是以父类的接口为准
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)) {
            // 遍历 interfaces 数组
            for (Type type : interfaces) {
                // 要求 type 是泛型参数
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    // 要求是 MessageHandler 接口
                    if (Objects.equals(parameterizedType.getRawType(), MessageHandler.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        // 取首个元素
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                            return (Class<Message>) actualTypeArguments[0];
                        } else {
                            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
    }
}
