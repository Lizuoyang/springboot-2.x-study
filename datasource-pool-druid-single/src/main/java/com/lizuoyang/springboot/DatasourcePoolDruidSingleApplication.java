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
public class DatasourcePoolDruidSingleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DatasourcePoolDruidSingleApplication.class, args);
    }

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try(Connection connection = dataSource.getConnection()) {
            log.info("spring boot [run][获得连接: {}]", connection);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
