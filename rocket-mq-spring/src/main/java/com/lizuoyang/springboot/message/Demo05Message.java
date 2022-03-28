package com.lizuoyang.springboot.message;

import lombok.Data;

/**
 * @ClassName Demo01Message
 * @Description message 05
 * @Author LiZuoYang
 * @Date 2022/3/28 14:26
 **/
@Data
public class Demo05Message {
    public static final String TOPIC = "DEMO_05";

    /**
     * 编号
     */
    private Integer id;
}
