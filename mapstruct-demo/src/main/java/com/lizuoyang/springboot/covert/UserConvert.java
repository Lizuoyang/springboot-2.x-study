package com.lizuoyang.springboot.covert;

import com.lizuoyang.springboot.entity.AccessTokenBO;
import com.lizuoyang.springboot.entity.UserBO;
import com.lizuoyang.springboot.entity.UserDO;
import com.lizuoyang.springboot.entity.UserDetailsBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author lizuoyang
 * @description 用户对象转换类
 * @date 13:50 2021/3/1
 **/
@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mapping(target = "userId", source = "id")
    UserBO do2bo (UserDO userDO);

    @Mapping(source = "tokenBO.accessToken", target = "token.accessToken")
    @Mapping(source = "tokenBO.expiresTime", target = "token.expiresTime")
    @Mapping(source = "userBO.userId", target = "id")
    UserDetailsBO do2detailsBo(UserBO userBO, AccessTokenBO tokenBO);
}
