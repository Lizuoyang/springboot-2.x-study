package com.lizuoyang.springboot.handle;

import com.lizuoyang.springboot.pojo.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName GlobalResponseBodyHandler
 * @Description 全局统一返回处理器
 * @Author LiZuoYang
 * @Date 2021/3/2 11:26
 **/
@ControllerAdvice("com.lizuoyang.springboot.controller")
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 如果已经是 ApiResponse 类型，则直接返回
        if (o instanceof ApiResponse) {
            return o;
        }
        // 如果不是，则包装成 ApiResponse 类型
        return ApiResponse.ofSuccess(o);
    }
}
