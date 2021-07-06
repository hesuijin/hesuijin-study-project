package com.example.demo.dataCreataTion;

import com.example.demo.model.Business;
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
public class CreateBusinessList {

    public List<Business> methodArray(){
        List<Business> businessList = new ArrayList<>();

        Business business1 = new Business();
        business1.setId(1001L);
        business1.setName("商家名称1");
        business1.setAddress("商家地址1");

        Business business2 = new Business();
        business2.setId(1002L);
        business2.setName("商家名称2");
        business2.setAddress("商家地址2");

        Business business3 = new Business();
        business3.setId(1003L);
        business3.setName("商家名称3");
        business3.setAddress("商家地址3");

        businessList.add(business1);
        businessList.add(business2);
        businessList.add(business3);

        return businessList;
    }
}
