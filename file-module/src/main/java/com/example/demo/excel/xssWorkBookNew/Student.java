package com.example.demo.excel.xssWorkBookNew;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/8/5
 */
@Data
public class Student {

    private Long id;

    private String name;

    private Integer age;

    private BigDecimal money;

    private String moneyFmt;
}
