package com.lizuoyang.springboot.message;

import lombok.Data;

/**
 * @ClassName Demo02Message
 * @Description Demo02Message
 * @Author LiZuoYang
 * @Date 2022/4/1 10:05
 **/
@Data
public class Demo02Message {
    public static final String TOPIC = "KAFKA_DEMO_02";

    private Integer id;
}
