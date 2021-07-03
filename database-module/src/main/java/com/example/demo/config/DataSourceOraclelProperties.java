package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description: 获取配置文件YML中的配置信息  生成数据库连接
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@Configuration
public class DataSourceOraclelProperties {

    /**
     * Mysql数据库数据源配置
     * @return 返回 Mysql数据库数据源配置
     */
    @Bean(name = "oracleDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSourceProperties deviceCenterDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Mysql数据库数据源
     * @param dataSourceProperties Mysql数据库数据源配置
     * @return 返回设 Mysql数据库数据源
     */
    @Bean(name = "oracleDataSource")
    public DataSource deviceCenterDataSource(@Qualifier("oracleDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}
