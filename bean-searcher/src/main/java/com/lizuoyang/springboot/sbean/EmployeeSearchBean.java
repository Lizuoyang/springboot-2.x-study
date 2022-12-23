package com.lizuoyang.springboot.sbean;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.DbIgnore;
import com.ejlchina.searcher.bean.SearchBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName EmployeeQuery
 * @Description 员工查询类
 * @Author LiZuoYang
 * @Date 2021/12/2 9:48
 **/
@Data
@SearchBean(tables = "employee e, department d", autoMapTo = "e", joinCond = "e.department_id = d.id")
public class EmployeeSearchBean {
    // 自动映射到 "e.id"
    private Long id;

    @DbField
    private String name;

    // 自动映射到 "e.age"
    private Integer age;

    @DbField("d.name")
    private String department;

    // 自动映射到 "e.entry_date"
    @JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime entryDate;

    // 该字段不会映射到数据表
    @DbIgnore
    private String ignoreField = "ignore";
}
