package com.lizuoyang.springboot.message;

import lombok.Data;

/**
 * @ClassName Demo04Message
 * @Description TODO
 * @Author LiZuoYang
 * @Date 2022/4/1 14:51
 **/
@Data
public class Demo04Message {
    public static final String TOPIC = "KAFKA_DEMO_04";

    private Integer id;

}
