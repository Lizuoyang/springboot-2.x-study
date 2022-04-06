package com.lizuoyang.springboot.mapper;

import com.lizuoyang.springboot.entity.UserDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName UserRepository
 * @Description user dao
 * @Author LiZuoYang
 * @Date 2022/4/6 16:51
 **/
public interface UserRepository extends MongoRepository<UserDO, String> {

    UserDO findByName(String userName);

    Page<UserDO> findByNameLike(String userName, Pageable pageable);
}
