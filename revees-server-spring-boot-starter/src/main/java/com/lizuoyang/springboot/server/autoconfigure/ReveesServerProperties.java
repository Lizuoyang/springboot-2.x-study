package com.lizuoyang.springboot.server.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName ReveesServerProperties
 * @Description 自定义Starter属性文件类
 * @Author LiZuoYang
 * @Date 2021/2/25 9:19
 **/
@ConfigurationProperties(prefix = "revees.server")
public class ReveesServerProperties {
    /**
     * 默认端口
     */
    private static final Integer DEFAULT_PORT = 8000;

    /**
     * 端口
     */
    private Integer port = DEFAULT_PORT;


    public static Integer getDefaultPort() {
        return DEFAULT_PORT;
    }

    public Integer getPort() {
        return port;
    }

    public ReveesServerProperties setPort(Integer port) {
        this.port = port;
        return this;
    }
}
