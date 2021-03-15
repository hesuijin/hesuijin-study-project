package com.example.demo.interceptor.config;

import com.example.demo.interceptor.myInterceptor.AdminInterceptor;
import com.example.demo.interceptor.myInterceptor.LogInterceptor;
import com.example.demo.interceptor.myInterceptor.OldLoginInterceptor;
import com.example.demo.interceptor.myInterceptor.AnnotationLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author HeSuiJin
 * @Date 2021/3/15 17:56
 * @Description:
 */
@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // LogInterceptor apply to all URLs.
        registry.addInterceptor(new LogInterceptor());

        // Old Login url, no longer use.
        // Use OldURLInterceptor to redirect to a new URL.
        registry.addInterceptor(new OldLoginInterceptor())
                .addPathPatterns("/admin/oldLogin");

        // This interceptor apply to URL like /admin/*
        // Exclude /admin/oldLogin
        // 避免/admin/* 再次打印 /admin/oldLogin的日志
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/admin/oldLogin");

        registry.addInterceptor(new AnnotationLogInterceptor())
                .addPathPatterns("/annotationLog");
    }

}
