package com.lizuoyang.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 主启动类
 *
 * @author lizuoyang
 * @date 2023/01/04
 */
@RestController
@SpringBootApplication
public class EasyExcelDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyExcelDemoApplication.class, args);
    }

    @GetMapping("/test")
    public String index() {
        return "Easy Excel Demo";
    }
}
