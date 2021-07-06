package com.example.demo.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/6
 */
@Data
public class OrderExtra {

    private Long orderId;

    private String orderNo;

    private Long memberId;

    private Long businessId;

    private BigDecimal amount;

    private String businessAddress;

    private String businessName;

    private String memberName;

    private Integer memberAge;

}
