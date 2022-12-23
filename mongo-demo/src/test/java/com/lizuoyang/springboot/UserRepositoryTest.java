package com.lizuoyang.springboot;

import com.lizuoyang.springboot.entity.UserDO;
import com.lizuoyang.springboot.mapper.UserDao;
import com.lizuoyang.springboot.mapper.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Optional;

/**
 * @ClassName UserRepositoryTest
 * @Description 用户测试类
 * @Author LiZuoYang
 * @Date 2022/4/6 16:55
 **/
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsert() {
        UserDO userDO = UserDO.builder().name("ljl").age(26)._id("1").build();
        userRepository.insert(userDO);
    }

    @Test
    public void testUpdate() {
        Optional<UserDO> optional = userRepository.findById("1");
        Assert.isTrue(optional.isPresent(), "用户不存在");
        // 获取对象，然后更新
        UserDO userDO = optional.get();
        userDO.setAge(18);
        userRepository.save(userDO);
    }

    @Test
    public void testSelectByIds() {
        Iterable<UserDO> userDOS = userRepository.findAllById(Arrays.asList("624d4df7c264452d7b53fd2a", "624d59eef846ff1ab1e7365d"));
        userDOS.forEach(System.out::println);
    }

    @Test
    public void testSelectById() {
        Optional<UserDO> userDO = userRepository.findById("1");
        System.out.println(userDO.get());
    }

    @Test
    public void testDeleteById() {
        userRepository.deleteById("1");
    }

    @Test
    public void testSelectByName() {
        UserDO userDO = userRepository.findByName("lzy");
        System.out.println(userDO);
    }

    @Test
    public void testSelectByNameLikePageAndSort() {
        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        // 创建分页条件。
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<UserDO> userDOPage = userRepository.findByNameLike("y", pageable);
        // 打印
        System.out.println(userDOPage.getTotalElements());
        System.out.println(userDOPage.getTotalPages());
    }

    @Test
    public void testUpdateByTemplate() {
        UserDO userDO = UserDO.builder().name("ljl").age(26)._id("624d4df7c264452d7b53fd2a").build();
        userDao.updateById(userDO);
    }

    @Test
    public void testSelectByTemplate() {
        UserDO byId = userDao.findById("624d4df7c264452d7b53fd2a");
        System.out.println(byId);
    }

}
