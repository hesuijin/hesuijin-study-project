package com.example.demo.interceptor.myAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 经过使用该 注解 经过拦截器时存储请求信息
 * @Author HeSuiJin
 * @Date 2021/3/15 21:15
 * @Description:
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterceptorAnnotationLog {
    boolean booleanValue() default true;
}
