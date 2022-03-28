package com.lizuoyang.springboot.message;

import lombok.Data;

/**
 * @ClassName Demo01Message
 * @Description message 03
 * @Author LiZuoYang
 * @Date 2022/3/28 14:26
 **/
@Data
public class Demo03Message {
    public static final String TOPIC = "DEMO_03";

    /**
     * 编号
     */
    private Integer id;
}
