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
     * 2：key值可以为空 或者为 空字符串
     * 3：使用key值获取map数据后，如果数据是对象类型，注意先对对象进行判断空
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

    /**
     * 使用
     *  BinaryOperator<U> mergeFunction) 方式可以快速的在转换Map时  对Map的key值进行去重
     */
    @Test
    public void  ListToMapJunit2(){
        List<Business> businessList1 = createBusinessList.methodArray();
        List<Business> businessList2 = createBusinessList.methodArray();
        businessList1.addAll(businessList2);
        Map<Long,Business> businessMap = businessList1.stream().collect(Collectors.toMap(Business::getId, business->business,(a,b)->a));
        System.out.println(JSONObject.toJSONString(businessMap));
    }
}
