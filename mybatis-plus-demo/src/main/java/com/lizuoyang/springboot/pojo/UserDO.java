package com.lizuoyang.springboot.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;

/**
 * @ClassName UserDO
 * @Description TODO
 * @Author LiZuoYang
 * @Date 2021/7/16 11:18
 **/
public class UserDO {
    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码（明文）
     *
     * ps：生产环境下，千万不要明文噢
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;
}
