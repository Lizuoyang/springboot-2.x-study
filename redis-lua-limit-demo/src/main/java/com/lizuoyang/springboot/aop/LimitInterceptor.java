package com.lizuoyang.springboot.aop;

import com.google.common.collect.ImmutableList;
import com.lizuoyang.springboot.annation.Limit;
import com.lizuoyang.springboot.enums.LimitType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.omg.CORBA.UNKNOWN;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 限流切面实现
 *
 * @author lizuoyang
 * @date 2022/12/28
 */
@Slf4j
@Aspect
@Configuration
public class LimitInterceptor {
    private static final String UNKNOWN = "unknown";
    private final RedisTemplate<String, Serializable> redisTemplate;

    public LimitInterceptor(RedisTemplate<String, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 切面具体实现
     *
     * @param point 点
     * @return {@link Object}
     */
    @Around("execution(public * *(..)) && @annotation(com.lizuoyang.springboot.annation.Limit)")
    public Object interceptor(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        Limit limitAnnation = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnnation.limitType();
        String name = limitAnnation.name();
        String key;
        int limitPeriod = limitAnnation.period();
        int limitCount = limitAnnation.count();

        /**
         * 根据限流类型获取不同的key ,如果不传我们会以方法名作为key
         */

        switch (limitType) {
            case IP:
                key = getIpAddress();
                break;
            case CUSTOMER:
                key = limitAnnation.key();
                break;
            default:
                key = method.getName().toUpperCase();
        }

        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnation.prefix(), key));
        try {
            String luaScript = buildLuaScript();
            RedisScript<Number> redisScript = new DefaultRedisScript<>(luaScript, Number.class);
            Number count = redisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
            log.info("Access try count is {} for name={} and key = {}", count, name, key);
            if (count != null && count.intValue() <= limitCount) {
                return point.proceed();
            } else {
                throw new RuntimeException("You have been dragged into the blacklist");
            }
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            throw new RuntimeException("server exception");
        }
    }

    /**
     * 构建lua脚本
     *
     * @return {@link String}
     */
    public String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append("local c");
        lua.append("\nc = redis.call('get',KEYS[1])");
        // 调用不超过最大值，则直接返回
        lua.append("\nif c and tonumber(c) > tonumber(ARGV[1]) then");
        lua.append("\nreturn c;");
        lua.append("\nend");
        // 执行计算器自加
        lua.append("\nc = redis.call('incr',KEYS[1])");
        lua.append("\nif tonumber(c) == 1 then");
        // 从第一次调用开始限流，设置对应键值的过期
        lua.append("\nredis.call('expire',KEYS[1],ARGV[2])");
        lua.append("\nend");
        lua.append("\nreturn c;");
        return lua.toString();
    }


    /**
     * 获得ip地址
     *
     * @return {@link String}
     */
    public String getIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
