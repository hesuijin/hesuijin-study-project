package com.example.demo.configMutiTransaction;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiTransactional {

    String[] values() default {};
}
