package com.lizuoyang.springboot.handle;

import com.lizuoyang.springboot.exception.ServiceException;
import com.lizuoyang.springboot.exception.ServiceExceptionEnum;
import com.lizuoyang.springboot.message.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingMatrixVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局统一异常处理
 * @Author LiZuoYang
 * @Date 2021/3/2 13:57
 **/
@ControllerAdvice(basePackages = "com.lizuoyang.springboot.controller")
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理 ServiceException 异常
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public ApiResponse serviceExceptionHandler(HttpServletRequest request, ServiceException serviceException) {
        log.debug("[serviceExceptionHandler]", serviceException);
        return ApiResponse.ofError(serviceException);
    }

    /**
     * 处理 MissingServletRequestParameterException 异常
     *
     * SpringMVC 参数不正确
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ApiResponse missServletRequestParameterExceptionHandler(HttpServletRequest request, ServiceException serviceException) {
        log.debug("[missServletRequestParameterExceptionHandler]", serviceException);
        return ApiResponse.ofError(ServiceExceptionEnum.MISSING_REQUEST_PARAM_ERROR);
    }

    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiResponse exceptionHandler(HttpServletRequest request, Exception exception) {
        log.debug("[exceptionHandler]", exception);
        return ApiResponse.ofError(ServiceExceptionEnum.SYS_ERROR);
    }

}
