package com.lizuoyang.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class HibernateValidateDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateValidateDemoApplication.class, args);
    }

}
