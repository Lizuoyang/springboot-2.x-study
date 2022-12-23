package com.lizuoyang.springboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description 数据库连接池配置
 * @Author LiZuoYang
 * @Date 2021/6/15 17:12
 **/
@Configuration
public class DataSourceConfig {

    /**
     * 创建 orders 数据源的配置对象
     * @return
     */
    @Primary
    @Bean(name = "orderDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.orders") // // 读取 spring.datasource.orders 配置到 DataSourceProperties 对象
    public DataSourceProperties orderDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 users 数据源的配置对象
     * @return
     */
    @Bean(name = "userDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.users") // // 读取 spring.datasource.users 配置到 DataSourceProperties 对象
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 创建 orders 数据源
     * @return
     */
    @Bean(name = "orderDataSource")
    public DataSource orderDataSource() {
        // 获取配置对象
        DataSourceProperties dataSourceProperties = this.orderDataSourceProperties();
        HikariDataSource hikariDataSource = createHikariDataSource(dataSourceProperties);
        return hikariDataSource;
    }

    /**
     * 创建 orders 数据源
     * @return
     */
    @Bean(name = "userDataSource")
    public DataSource userDataSource() {
        // 获取配置对象
        DataSourceProperties dataSourceProperties = this.userDataSourceProperties();
        DataSource hikariDataSource = createHikariDataSource(dataSourceProperties);
        return hikariDataSource;
    }

    private static HikariDataSource createHikariDataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        // 设置线程池名称
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        return dataSource;
    }
}
