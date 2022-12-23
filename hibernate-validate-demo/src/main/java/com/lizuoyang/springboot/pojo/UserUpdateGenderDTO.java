package com.lizuoyang.springboot.pojo;

import com.lizuoyang.springboot.annotation.InEnum;
import com.lizuoyang.springboot.enums.GenderEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UserUpdateGenderDTO
 * @Description TODO
 * @Author LiZuoYang
 * @Date 2021/3/10 16:16
 **/
@Data
public class UserUpdateGenderDTO {
    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer id;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    @InEnum(value = GenderEnum.class, message = "性别必须是 {value}")
    private Integer gender;
}
