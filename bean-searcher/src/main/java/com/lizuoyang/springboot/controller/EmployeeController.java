package com.lizuoyang.springboot.controller;

import com.ejlchina.searcher.BeanSearcher;
import com.ejlchina.searcher.MapSearcher;
import com.ejlchina.searcher.SearchResult;
import com.ejlchina.searcher.util.MapUtils;
import com.lizuoyang.springboot.sbean.EmployeeSearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 员工请求控制器
 * @Author LiZuoYang
 * @Date 2021/12/2 9:19
 **/
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    /**
     * 注入检索器
     */
    @Autowired
    private BeanSearcher beanSearcher;

    @GetMapping("/index")
    public Object index(@RequestParam Map<String, Object> params) {
        return beanSearcher.search(EmployeeSearchBean.class, params, new String[]{"age"});
    }

    @GetMapping("/count")
    public Object count(@RequestParam Map<String, Object> params) {
        return beanSearcher.searchCount(EmployeeSearchBean.class, params);
    }
}
