package com.lizuoyang.springboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ClassName WebSocketConfiguration
 * @Description WebSocket 配置类
 * @Author LiZuoYang
 * @Date 2021/3/11 14:30
 **/
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev")
@Configuration
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
