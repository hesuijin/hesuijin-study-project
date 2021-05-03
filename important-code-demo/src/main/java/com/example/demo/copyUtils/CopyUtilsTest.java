package com.example.demo.copyUtils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/5/3
 */
public class CopyUtilsTest {
    public static void main(String[] args) {

        beanUtilsTest();

       jsonObjectCopyTest();
    }

    private static void beanUtilsTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setString("我是String");
        userDTO.setArrayList(new ArrayList());

        UserDTO userDTOCopy = new UserDTO();
        BeanUtils.copyProperties(userDTO,userDTOCopy);

        // 判断详情对对象是否相同，预期值(false)
        System.out.println(userDTO );
        System.out.println(userDTOCopy);

        System.out.println("beanUtilsTest 对比基本数据(包装类)类型:"+(userDTO.getId() == userDTOCopy.getId()) );
        System.out.println("beanUtilsTest 对比String:" +(  userDTO.getString() == userDTOCopy.getString()) );
        System.out.println( "beanUtilsTest 对比引用数据类型:" +(userDTO.getArrayList() == userDTOCopy.getArrayList()) );
    }

    private static void jsonObjectCopyTest(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setString("我是String");
        userDTO.setArrayList(new ArrayList());
        UserDTO userDTOCopy = JSONObject.parseObject(JSONObject.toJSONString(userDTO), UserDTO.class);

        // 判断详情对对象是否相同，预期值(false)
        System.out.println(userDTO );
        System.out.println(userDTOCopy);

        System.out.println("jsonObjectCopyTest 对比基本数据(包装类)类型:"+(userDTO.getId() == userDTOCopy.getId()) );
        System.out.println("jsonObjectCopyTest 对比String:" +(  userDTO.getString() == userDTOCopy.getString()) );
        System.out.println( "jsonObjectCopyTest 对比引用数据类型:" +(userDTO.getArrayList() == userDTOCopy.getArrayList()) );
    }
}
