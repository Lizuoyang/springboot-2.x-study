package com.lizuoyang.springboot.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description 数据源连接池配置
 * @Author LiZuoYang
 * @Date 2021/6/15 19:13
 **/
@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "ordersDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.orders")
    public DataSource ordersDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "usersDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.users")
    public DataSource usersDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
