//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//
///**
// * @Description:
// * @Author HeSuiJin
// * @Date 2022/1/30
// */
//@Configuration
//@PropertySource("classpath:/redis/redis.properties")
//public class RedisConfig {
//
//    @Bean
//    public JedisPool redisPoolFactory() throws Exception {
//        logger.info("JedisPool注入成功！！");
//        logger.info("redis地址：" + host + ":" + port);
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
//        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
//        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
//        // 是否启用pool的jmx管理功能, 默认true
//        jedisPoolConfig.setJmxEnabled(true);
//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, database);
//        return jedisPool;
//    }
//
//}
