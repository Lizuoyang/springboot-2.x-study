package com.lizuoyang.springboot.enums;

import com.lizuoyang.springboot.interfaces.IntArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @ClassName GenderEnum
 * @Description 性别取值范围枚举
 * @Author LiZuoYang
 * @Date 2021/3/10 15:56
 **/
@Getter
@AllArgsConstructor
public enum  GenderEnum implements IntArrayValuable {
    MALE(1, "男"),
    FEMALE(2, "女");

    /**
     * 值数组
     */
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(GenderEnum::getValue).toArray();

    /**
     * 性别值
     */
    private final Integer value;
    /**
     * 性别名
     */
    private final String name;

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
