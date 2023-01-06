package com.lizuoyang.springboot.enums;

/**
 * 限流类型枚举类
 *
 * @author lizuoyang
 * @date 2022/12/28
 */
public enum LimitType {
    /**
     * 自定义key
     */
    CUSTOMER,

    /**
     * IP
     */
    IP
    ;
}
