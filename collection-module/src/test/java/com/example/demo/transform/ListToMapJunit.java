package com.example.demo.transform;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dataCreataTion.CreateBusinessList;
import com.example.demo.dataCreataTion.CreateMemberList;
import com.example.demo.dataCreataTion.CreateOrderList;
import com.example.demo.model.Business;
import com.example.demo.model.Member;
import com.example.demo.model.Order;
import com.example.demo.model.OrderExtra;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: List 转为Map
 * @Author HeSuiJin
 * @Date 2021/7/6
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ListToMapJunit {

    @Autowired
    private CreateBusinessList createBusinessList;

    @Autowired
    private CreateMemberList createMemberList;

    @Autowired
    private CreateOrderList createOrderList;

    /**
     * 注意
     * 1：转换为 map的时候 key值必须是唯一的
     * 2：key值不能为空
     * 3：使用key值获取map数据注意判断空
     */
    @Test
    public void  ListToMapJunit1(){
        List<OrderExtra> orderExtraList = new ArrayList<>();

        List<Order> orderList = createOrderList.methodArray();
        List<Member> memberList = createMemberList.methodArray();
        List<Business> businessList = createBusinessList.methodArray();

        orderList.stream().forEach(order->{
            OrderExtra orderExtra = new OrderExtra();
            orderExtra.setOrderId(order.getId());
            orderExtra.setAmount(order.getAmount());
            orderExtra.setBusinessId(order.getBusinessId());
            orderExtra.setMemberId(order.getMemberId());
            orderExtra.setOrderNo(order.getOrderNo());
            orderExtraList.add(orderExtra);
        });

        //orderExtraList数据组装 member的数据 (先转换为Map)
        Map<Long,Member> memberMap = memberList.stream().collect(Collectors.toMap(member->member.getId(), member->member));

        //orderExtraList数据组装 business的数据(先转换为Map)
        Map<Long,Business> businessMap = businessList.stream().collect(Collectors.toMap(Business::getId, business->business));

        Map<Long,Business> businessMap1 = businessList.stream().collect(Collectors.toMap(Business::getId, business->business,(a,b)->a));


        orderExtraList.stream().forEach(orderExtra -> {
            Member member =  memberMap.get(orderExtra.getMemberId());
            orderExtra.setMemberAge(member.getAge());
            orderExtra.setMemberName(member.getName());

            Business business =  businessMap.get(orderExtra.getBusinessId());
            orderExtra.setBusinessAddress(business.getAddress());
            orderExtra.setBusinessName(business.getName());
        });

        log.info("OrderExtra数组打印组装数据：{}",JSONObject.toJSONString(orderExtraList));
    }
}
