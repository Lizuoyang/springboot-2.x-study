package com.lizuoyang.springboot;

import com.lizuoyang.springboot.covert.UserConvert;
import com.lizuoyang.springboot.entity.AccessTokenBO;
import com.lizuoyang.springboot.entity.UserBO;
import com.lizuoyang.springboot.entity.UserDO;
import com.lizuoyang.springboot.entity.UserDetailsBO;

import java.util.UUID;

/**
 * @ClassName UserTest
 * @Description 测试类
 * @Author LiZuoYang
 * @Date 2021/3/1 13:53
 **/
public class UserTest {
    public static void main(String[] args) {
        UserDO userDO = UserDO.builder().id(1).username("lizuoyang").password("qwe22515").build();
        UserBO userBO = UserConvert.INSTANCE.do2bo(userDO);
        System.out.println(userBO);

        AccessTokenBO accessTokenBO = AccessTokenBO.builder()
                .accessToken(UUID.randomUUID().toString())
                .expiresTime("2021-03-01 15:00:00")
                .build();
        UserDetailsBO detailsBO = UserConvert.INSTANCE.do2detailsBo(userBO, accessTokenBO);
        System.out.println(detailsBO);
    }
}
