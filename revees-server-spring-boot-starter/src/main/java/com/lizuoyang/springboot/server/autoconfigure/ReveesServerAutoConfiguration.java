package com.lizuoyang.springboot.server.autoconfigure;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @ClassName ReveesServerAutoConfiguration
 * @Description 自定义starter 自动配置类
 * @Author LiZuoYang
 * @Date 2021/2/25 9:28
 **/
@Configuration // 声明这是一个配置类
@EnableConfigurationProperties(ReveesServerProperties.class) // 使配置类生效
public class ReveesServerAutoConfiguration {

    private Logger log = LoggerFactory.getLogger(ReveesServerAutoConfiguration.class);

    @Bean
    @ConditionalOnClass(HttpServer.class)
    public HttpServer httpServer(ReveesServerProperties properties) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(properties.getPort()), 0);
        httpServer.start();
        log.info("[httpServer][启动服务成功，端口为]" +   properties.getPort());
        return httpServer;
    }
}
