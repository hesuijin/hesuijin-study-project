package com.example.demo.filter.config;

import com.example.demo.filter.myFilter.MyFilterOne;
import com.example.demo.filter.myFilter.MyFilterTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author HeSuiJin
 * @Date 2021/3/14 20:38
 * @Description:
 */
@Configuration
public class MyFilterConfig {

    @Autowired
    MyFilterOne myFilterOne;

    @Autowired
    MyFilterTwo myFilterTwo;

    @Bean
    public FilterRegistrationBean<MyFilterOne> setUpMyFilterOne() {
        FilterRegistrationBean<MyFilterOne> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(myFilterOne);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/hello/*")));

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<MyFilterTwo> setUpMyFilterTwo() {
        FilterRegistrationBean<MyFilterTwo> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setFilter(myFilterTwo);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/hello/*")));

        return filterRegistrationBean;
    }
}
