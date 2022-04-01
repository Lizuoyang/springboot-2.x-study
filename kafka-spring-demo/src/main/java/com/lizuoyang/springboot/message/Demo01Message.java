package com.lizuoyang.springboot.message;

import lombok.Data;

/**
 * @ClassName Demo01Message
 * @Description Demo01Message
 * @Author LiZuoYang
 * @Date 2022/4/1 10:05
 **/
@Data
public class Demo01Message {
    public static final String TOPIC = "KAFKA_DEMO_01";

    private Integer id;
}
