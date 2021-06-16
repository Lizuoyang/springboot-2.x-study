package com.lizuoyang.springboot.message;

import com.lizuoyang.springboot.exception.ServiceException;
import com.lizuoyang.springboot.exception.ServiceExceptionEnum;
import lombok.Data;

/**
 * @ClassName ApiResponse
 * @Description 通用的 API 接口封装
 * @Author LiZuoYang
 * @Date 2021/1/19 14:24
 **/
@Data
public class ApiResponse {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回内容
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 无参构造函数
     */
    private ApiResponse() {

    }

    /**
     * 全参构造函数
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     */
    private ApiResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造一个自定义的API返回
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     * @return ApiResponse
     */
    public static ApiResponse of(Integer code, String message, Object data) {
        return new ApiResponse(code, message, data);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data 返回数据
     * @return ApiResponse
     */
    public static ApiResponse ofSuccess(Object data) {
        return ofStatus(ServiceExceptionEnum.SUCCESS, data);
    }

    /**
     * 构造一个成功不带数据的API返回
     *
     * @return ApiResponse
     */
    public static ApiResponse ofSuccess() {
        return ofStatus(ServiceExceptionEnum.SUCCESS, null);
    }

    public static ApiResponse ofError(ServiceException exception) {
        return of(exception.getCode(), exception.getMessage(), null);
    }

    public static ApiResponse ofError(ServiceExceptionEnum exceptionEnum) {
        return ofStatus(exceptionEnum);
    }

    /**
     * 构造一个成功且自定义消息的API返回
     *
     * @param message 返回内容
     * @return ApiResponse
     */
    public static ApiResponse ofMessage(String message) {
        return of(ServiceExceptionEnum.SUCCESS.getCode(), message, null);
    }

    /**
     * 构造一个有状态的API返回
     *
     * @param status 状态 {@link ServiceExceptionEnum}
     * @return ApiResponse
     */
    public static ApiResponse ofStatus(ServiceExceptionEnum status) {
        return ofStatus(status, null);
    }

    /**
     * 构造一个有状态且带数据的API返回
     *
     * @param status 状态 {@link ServiceExceptionEnum}
     * @param data   返回数据
     * @return ApiResponse
     */
    public static ApiResponse ofStatus(ServiceExceptionEnum status, Object data) {
        return of(status.getCode(), status.getMessage(), data);
    }
}
