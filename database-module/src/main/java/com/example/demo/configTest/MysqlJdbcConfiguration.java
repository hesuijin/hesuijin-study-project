//package com.example.demo.configTest;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
///**
// * @Author hesuijin
// * @Description
// * @Param
// * @Return
// * @Date 2021/3/4
// */
//@Configuration
//@EnableConfigurationProperties(MysqlJdbcProperties.class)
//@Slf4j
//public class MysqlJdbcConfiguration {
//    @Bean
//    public DataSource dataSource(MysqlJdbcProperties jdbcProperties) {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setPassword(jdbcProperties.getPassword());
//        dataSource.setUsername(jdbcProperties.getUsername());
//        dataSource.setUrl(jdbcProperties.getUrl());
//        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
//        return dataSource;
//    }
//}
