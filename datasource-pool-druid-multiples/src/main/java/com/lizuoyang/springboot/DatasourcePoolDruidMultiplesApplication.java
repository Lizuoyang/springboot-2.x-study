package com.lizuoyang.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
public class DatasourcePoolDruidMultiplesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DatasourcePoolDruidMultiplesApplication.class, args);
    }

    @Resource(name = "ordersDataSource")
    private DataSource ordersDataSource;

    @Resource(name = "usersDataSource")
    private DataSource usersDataSource;

    @Override
    public void run(String... args) throws Exception {
        log.info("[run][获得数据源：{}]", ordersDataSource);
        log.info("[run][获得数据源：{}]", usersDataSource);
    }
}
