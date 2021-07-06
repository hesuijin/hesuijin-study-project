package com.example.demo.dataCreataTion;

import com.example.demo.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/6
 */
@Component
public class CreateOrderList {

    public List<Order> methodArray(){
        List<Order> orderList = new ArrayList<>();

        Order order1 = new Order();
        order1.setId(1L);
        order1.setMemberId(101L);
        order1.setBusinessId(1001L);
        order1.setOrderNo("000001");
        order1.setAmount(new BigDecimal(10));

        Order order2 = new Order();
        order2.setId(2L);
        order2.setMemberId(102L);
        order2.setBusinessId(1002L);
        order2.setOrderNo("000002");
        order2.setAmount(new BigDecimal(10));

        Order order3 = new Order();
        order3.setId(3L);
        order3.setMemberId(103L);
        order3.setBusinessId(1003L);
        order3.setOrderNo("000003");
        order3.setAmount(new BigDecimal(10));

        Order order4 = new Order();
        order4.setId(4L);
        order4.setMemberId(102L);
        order4.setBusinessId(1002L);
        order4.setOrderNo("000004");
        order4.setAmount(new BigDecimal(5));

        Order order5 = new Order();
        order5.setId(5L);
        order5.setMemberId(103L);
        order5.setBusinessId(1003L);
        order5.setOrderNo("000005");
        order5.setAmount(new BigDecimal(5));

        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);

        return orderList;
    }

}
