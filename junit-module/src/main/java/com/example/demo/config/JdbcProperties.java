package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/4
 */
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix ="spring.datasource")
@Data
public class JdbcProperties {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
}
