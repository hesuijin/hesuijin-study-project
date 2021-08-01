package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.modules.mysqlModule.base.model.Member;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/8/1
 */
public class Variables {

    public static void main(String[] args) {
        Member member = new Member();
        member.setId(1L);
        member.setAge(18);
        member.setName("HeSuiJin");
        System.out.println(JSONObject.toJSONString(member));
        System.out.println(JSONObject.toJSONString(member));
    }
}
