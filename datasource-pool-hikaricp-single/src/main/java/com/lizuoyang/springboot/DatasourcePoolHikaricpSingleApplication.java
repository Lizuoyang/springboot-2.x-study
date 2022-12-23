package com.lizuoyang.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class DatasourcePoolHikaricpSingleApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(DatasourcePoolHikaricpSingleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try(Connection connection = dataSource.getConnection()) {
            log.info("spring boot [run][获得连接: {}]", connection);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

}
