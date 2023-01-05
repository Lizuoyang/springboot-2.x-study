package com.lizuoyang.springboot.pojo.eo;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 读取类
 *
 * @author lizuoyang
 * @date 2023/01/04
 */
@Getter
@Setter
@EqualsAndHashCode
public class DemoDataRead {
    private String name;
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private String birthDay;
    private Double money;
}
