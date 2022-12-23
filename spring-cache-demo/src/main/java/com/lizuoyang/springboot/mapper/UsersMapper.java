package com.lizuoyang.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizuoyang.springboot.entity.UsersDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2022-01-04
 */
@CacheConfig(cacheNames = "users")
public interface UsersMapper extends BaseMapper<UsersDO> {

    @Cacheable(key = "#id")
    UsersDO selectById(@Param("id") Integer id);

    @CachePut(key = "#usersDO.id")
    default UsersDO inser0(UsersDO usersDO) {
        this.insert(usersDO);
        return usersDO;
    }

    @CacheEvict(key = "#id")
    int deleteById(@Param("id") Integer id);
}
