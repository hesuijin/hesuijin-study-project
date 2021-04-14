package com.example.rocketmq.demo.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/4/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long orderId;

    private String orderType;



}
