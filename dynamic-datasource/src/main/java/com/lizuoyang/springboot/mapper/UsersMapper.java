package com.lizuoyang.springboot.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.lizuoyang.springboot.constants.DBConstants;
import com.lizuoyang.springboot.entity.UsersDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-07
 */
@DS(DBConstants.DATASOURCE_USERS)
public interface UsersMapper extends BaseMapper<UsersDO> {

}
