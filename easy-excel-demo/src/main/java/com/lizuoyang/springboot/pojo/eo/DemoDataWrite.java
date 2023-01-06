package com.lizuoyang.springboot.pojo.eo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.util.Date;

/**
 * 写入类
 *
 * @author lizuoyang
 * @date 2023/01/05
 */
@Data
public class DemoDataWrite {
    @ExcelProperty(value = "姓名", index = 0)
    private String name;
    @ExcelProperty(value = "生日", index = 1)
    private Date birthDay;
    @ExcelProperty(value = "红包金额", index = 2)
    private Double money;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
