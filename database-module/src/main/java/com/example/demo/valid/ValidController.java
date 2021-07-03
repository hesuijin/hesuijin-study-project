package com.example.demo.valid;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.valid.base.Member;
import com.example.demo.valid.base.ValidRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.fasterxml.jackson.databind.util.ISO8601Utils.format;

//表单中什么是空（null）： 左边没有key
//表单中什么是空值（如空字符串）：右边没有value

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/6/27
 */
@RestController
@RequestMapping("validController")
@Slf4j
public class ValidController {

    @GetMapping("validGetTest")
    public String validGetTest(@Valid ValidRequest validRequest) {
        log.info("validGetTest 入参：{}", JSONObject.toJSONString(validRequest));
        return "validGetTest success";
    }

    @PostMapping("validPostTest")
    public String validPostTest(@Valid ValidRequest validRequest) {
        log.info("validPostTest 入参：{}", JSONObject.toJSONString(validRequest));
        return "validPostTest success";
    }

    @GetMapping("validGetRequestBodyTest")
    public String validGetRequestBodyTest(@Valid @RequestBody ValidRequest validRequest) {
        log.info("validGetRequestBodyTest 入参：{}", JSONObject.toJSONString(validRequest));
        return "validGetRequestBodyTest success";
    }

//    public static void main(String[] args) {
////        Date date = new Date();
//////        new SimpleDateFormat("yyyyMMdd24hhmiss")format*()
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMDDHH24mmss");
////        simpleDateFormat.format(new Date());
////        System.out.println(simpleDateFormat.format(new Date()));
////        System.out.println(DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss"));
//
//        List<String> stringList1 = new ArrayList<>();
//        String[] strings ={"1","2"};
//        stringList1 = Arrays.asList(strings);
//        JSONObject.toJSONString(stringList1);
//
//        List<String> stringList2 = new ArrayList<>();
////        stringList1.add("1");
////        stringList1.add("2");
//
//        stringList2.add("1");
//        stringList2.add("2");
////        stringList2.add("3");
////        stringList2.add("4");
//
//        JSONObject.toJSONString(stringList2);
//        stringList1.removeAll(stringList2);
//
//        System.out.println(JSONObject.toJSONString(stringList1));
//
//        stringList1.stream()
//                .filter(a -> !stringList2.contains(a))
//        .forEach(b->System.out.println("我是打印啊"+b));
//
////        stringList1.stream().forEach(a->System.out.println("我是打印啊"+a));
//
////        !stringList.contains( a)
//
//    }
//
////    public static void main(String[] args) {
////        List<Member> memberList = new ArrayList<>();
////        Member member =new Member();
////        member.setAge("20");
////
////        Member member2 =new Member();
////        member2.setAge("201");
////
////        memberList.add(member);
////        memberList.add(member2);
////
////        memberList.stream().forEach(m->{m.setAge("90");});
////
////        for(Member m :memberList){
////            m.setAge("30");
////        }
////
////        System.out.println(JSONObject.toJSONString(memberList));
////
////        Member member1 = new Member();
////        member1.setAge("10");
////
////        List<Member> memberList = new ArrayList<>();
////        memberList.add(member1);
////
////        member1.setAge("20");
////
//////        !memberList.isEmpty()
////
////        System.out.println(JSONObject.toJSONString(memberList));
////
////    }
//}

//String[] 转 List<String> 后  无法使用其功能
//rrays.asList(strings);
//https://blog.csdn.net/weixin_42299645/article/details/114136943
//1. java.util.ArrayList
//2. java.util.Arrays￥ArrayList


    public static void main(String[] args) {
        String[] a = {};
        if (a ==null && a.length>0){
            System.out.println("shi");
        }
    }

}