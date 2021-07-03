package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@Configuration
public class DataSourceMysqlProperties {

    /**
     * Mysql数据库数据源配置
     * @return 返回 Mysql数据库数据源配置
     */
    @Bean(name = "mysqlDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSourceProperties deviceCenterDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Mysql数据库数据源
     * @param dataSourceProperties Mysql数据库数据源配置
     * @return 返回设 Mysql数据库数据源
     */
    @Bean(name = "mysqlDataSource")
    public DataSource deviceCenterDataSource(@Qualifier("mysqlDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}
