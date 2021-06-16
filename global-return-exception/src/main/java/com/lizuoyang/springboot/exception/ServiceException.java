package com.lizuoyang.springboot.exception;

import lombok.Data;
import lombok.Getter;

/**
 * @ClassName ServiceException
 * @Description 通用异常类
 * @Author LiZuoYang
 * @Date 2021/3/2 13:57
 **/
@Getter
public final class ServiceException extends RuntimeException{
    /**
     * 错误码
     */
    private final Integer code;

    public ServiceException(ServiceExceptionEnum serviceExceptionEnum) {
        // 使用父类的 message 字段
        super(serviceExceptionEnum.getMessage());
        // 设置错误码
        this.code = serviceExceptionEnum.getCode();
    }
}
