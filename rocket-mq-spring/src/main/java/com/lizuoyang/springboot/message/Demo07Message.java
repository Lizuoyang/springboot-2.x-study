package com.lizuoyang.springboot.message;

import lombok.Data;

/**
 * @ClassName Demo01Message
 * @Description message 07
 * @Author LiZuoYang
 * @Date 2022/3/28 14:26
 **/
@Data
public class Demo07Message {
    public static final String TOPIC = "DEMO_07";

    /**
     * 编号
     */
    private Integer id;
}
