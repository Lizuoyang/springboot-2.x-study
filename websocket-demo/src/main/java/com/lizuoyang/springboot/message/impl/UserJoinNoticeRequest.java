package com.lizuoyang.springboot.message.impl;

import com.lizuoyang.springboot.message.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserJoinNoticeRequest
 * @Description 用户成功认证请求广播
 * @Author LiZuoYang
 * @Date 2021/3/11 14:56
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinNoticeRequest implements Message {
    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    /**
     * 用户昵称
     */
    private String nickName;
}
