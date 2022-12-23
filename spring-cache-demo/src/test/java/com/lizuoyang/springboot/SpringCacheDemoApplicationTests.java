package com.lizuoyang.springboot;

import com.lizuoyang.springboot.entity.UsersDO;
import com.lizuoyang.springboot.mapper.UsersMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

import java.time.LocalDateTime;

@SpringBootTest
class SpringCacheDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    private static final String CACHE_NAME_USER = "users";

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void test01() {
        System.out.println(cacheManager);

        Integer id = 1;

        // 查询是否存在数据
        UsersDO usersDO = usersMapper.selectById(id);
        System.out.println("userDO: " + usersDO);
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(usersDO.getId(), usersDO.getClass()));

        usersDO = usersMapper.selectById(id);
        System.out.println("userDO: " + usersDO);
    }

    @Test
    void test02() {
        UsersDO usersDO = new UsersDO();
        usersDO.setId(4);
        usersDO.setUsername("测试2");
        usersDO.setPassword("123456");
        usersDO.setCreateTime(LocalDateTime.now());
        usersMapper.inser0(usersDO);
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(usersDO.getId(), usersDO.getClass()));
    }

    @Test
    void test03() {
        UsersDO usersDO = new UsersDO();
        usersDO.setId(5);
        usersDO.setUsername("测试3");
        usersDO.setPassword("123456");
        usersDO.setCreateTime(LocalDateTime.now());
        usersMapper.inser0(usersDO);
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(usersDO.getId(), usersDO.getClass()));


        // 删除缓存中的记录
        usersMapper.deleteById(usersDO.getId());
        Assert.assertNotNull("缓存为空", cacheManager.getCache(CACHE_NAME_USER).get(usersDO.getId(), usersDO.getClass()));

    }

}
