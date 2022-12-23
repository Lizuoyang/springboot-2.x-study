package com.lizuoyang.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class DatasourcePoolHikaricpMultipleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DatasourcePoolHikaricpMultipleApplication.class, args);
    }

    @Resource(name = "orderDataSource")
    private DataSource orderDataSource;

    @Resource(name = "userDataSource")
    private DataSource userDataSource;

    @Override
    public void run(String... args) throws Exception {
        try(Connection connection = orderDataSource.getConnection()) {
            log.info("spring boot [orderDataSource][获得连接: {}]", connection);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        try(Connection connection = userDataSource.getConnection()) {
            log.info("spring boot [userDataSource][获得连接: {}]", connection);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

    }
}
