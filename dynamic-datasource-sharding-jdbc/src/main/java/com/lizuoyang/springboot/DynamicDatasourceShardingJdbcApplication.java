package com.lizuoyang.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan("com.lizuoyang.springboot.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class DynamicDatasourceShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceShardingJdbcApplication.class, args);
    }

}
