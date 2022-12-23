package com.lizuoyang.springboot.handle;

import com.lizuoyang.springboot.exception.ServiceExceptionEnum;
import com.lizuoyang.springboot.pojo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

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
     * 处理 MissingServletRequestParameterException 异常
     *
     * SpringMVC 参数不正确
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ApiResponse constraintViolationExceptionExceptionHandler(HttpServletRequest request, ConstraintViolationException serviceException) {
        log.info("[constraintViolationExceptionExceptionHandler]", serviceException);

        //拼接错误
        StringBuilder builder = new StringBuilder();
        serviceException.getConstraintViolations().stream().forEach(x -> {
            if (builder.length() > 0) {
                builder.append(";");
            }
            builder.append(x.getMessage());
        });
        return ApiResponse.ofMessage(ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getCode(), ServiceExceptionEnum.INVALID_REQUEST_PARAM_ERROR.getMessage() + builder.toString(), null);
    }

    /**
     * 处理其它 Exception 异常
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiResponse exceptionHandler(HttpServletRequest request, Exception exception) {
        log.info("[exceptionHandler]", exception);
        return ApiResponse.ofError(ServiceExceptionEnum.SYS_ERROR);
    }

}
