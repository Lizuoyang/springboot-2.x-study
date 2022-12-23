package com.lizuoyang.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lizuoyang.springboot.mapper")
@SpringBootApplication
public class SubLibraryAndSubTableApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubLibraryAndSubTableApplication.class, args);
    }

}
