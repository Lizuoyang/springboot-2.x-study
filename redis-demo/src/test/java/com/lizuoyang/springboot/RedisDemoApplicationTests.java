package com.lizuoyang.springboot;

import com.lizuoyang.springboot.pojo.UserCacheDO;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class RedisDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void test01() {
        stringRedisTemplate.opsForValue().set("lizuoyang", "shuai");
    }

    @Test
    void test02() {
        redisTemplate.opsForValue().set("lijiali", "baozi");
    }

    @Test
    void test03() {
        UserCacheDO userCacheDO = UserCacheDO.builder()
                .id(1)
                .name("lizuoyang")
                .gender(1)
                .build();
        String key = String.format("user:%d", 1);
        redisTemplate.opsForValue().set(key, userCacheDO);
    }

    @Test
    void test04() {
        UserCacheDO userCacheDO = UserCacheDO.builder()
                .id(1)
                .name("lizuoyang")
                .gender(1)
                .build();
        String key = String.format("user:%d", userCacheDO.getId());
        redisTemplate.opsForValue().set(key, userCacheDO);
    }

    @Test
    void test05() {
        UserCacheDO user01= UserCacheDO.builder()
                .id(1)
                .name("lizuoyang")
                .gender(1)
                .build();

        UserCacheDO user02= UserCacheDO.builder()
                .id(2)
                .name("lijiali")
                .gender(0)
                .build();

        List<UserCacheDO> cacheDOS = Arrays.asList(user01, user02);
        redisTemplate.opsForList().leftPush("user:list:1",  cacheDOS);

    }

    @Test
    void test06() {
        List<UserCacheDO> list = (List<UserCacheDO>) redisTemplate.opsForList().leftPop("user:list:1");
        System.out.println(list);
    }

    @Test
    void test07() throws IOException {
        // 读取 /resources/lua/compareAndSet.lua 脚本
        String scriptContents  = IOUtils.toString(getClass().getResourceAsStream("/lua/compareAndSet.lua"), "UTF-8");
        // 创建 RedisScript 对象
        RedisScript<Long> redisScript = new DefaultRedisScript<>(scriptContents, Long.class);
        // 执行lua脚本
        Long execute = stringRedisTemplate.execute(redisScript, Collections.singletonList("lizuoyang"), "shuai", "shuai01");
        System.out.println(execute);
    }

}
