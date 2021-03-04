package com.example.jwt.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/4
 */
@ConfigurationProperties(prefix ="spring.datasource")
@Data
public class JdbcProperties {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
}
