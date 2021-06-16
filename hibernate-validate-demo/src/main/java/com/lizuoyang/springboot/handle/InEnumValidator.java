package com.lizuoyang.springboot.handle;

import com.lizuoyang.springboot.annotation.InEnum;
import com.lizuoyang.springboot.interfaces.IntArrayValuable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName InEnumValidator
 * @Description 自定义校验逻辑
 * @Author LiZuoYang
 * @Date 2021/3/10 16:07
 **/
public class InEnumValidator implements ConstraintValidator<InEnum, Integer> {
    private Set<Integer> values;

    @Override
    public void initialize(InEnum annotation) {
        IntArrayValuable[] enumConstants = annotation.value().getEnumConstants();
        if (enumConstants.length == 0) {
            this.values = Collections.emptySet();
        } else {
            this.values = Arrays.stream(enumConstants[0].array()).boxed().collect(Collectors.toSet());
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        // 校验通过
        if (values.contains(value)) {
            return true;
        }

        // 校验不通过
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate().replaceAll("\\{value}", values.toString())).addConstraintViolation();
        return false;
    }
}
