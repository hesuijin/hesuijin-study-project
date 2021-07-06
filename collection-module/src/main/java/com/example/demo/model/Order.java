package com.example.demo.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:订单类
 * @Author HeSuiJin
 * @Date 2021/7/6
 */
@Data
public class Order {

    private Long id;

    private String orderNo;

    private Long memberId;

    private Long businessId;

    private BigDecimal amount;
}
