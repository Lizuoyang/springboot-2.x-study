package com.lizuoyang.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lizuoyang.springboot.entity.DepartmentDO;
import com.lizuoyang.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mybatis-plus-general
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/get/{id}")
    public Object getDepartment(@PathVariable("id") String id) {
        DepartmentDO departmentDO = departmentService.getById(id);
        return departmentDO;
    }

    @GetMapping("/get")
    public Object getByParams(@RequestParam(value = "name") String name) {
        LambdaQueryWrapper<DepartmentDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DepartmentDO::getName, name);
        DepartmentDO departmentDO = departmentService.getOne(lambdaQueryWrapper);
        return departmentDO;
    }

    @GetMapping("/list")
    public Object listByParams() {
        return departmentService.list();
    }


}
