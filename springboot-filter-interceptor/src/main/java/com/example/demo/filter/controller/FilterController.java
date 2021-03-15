package com.example.demo.filter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author HeSuiJin
 * @Date 2021/3/14 18:47
 * @Description:
 */
@RestController
@RequestMapping("/api")
public class FilterController {

    @GetMapping("/hello")
    public String getConfigurationFilter() throws InterruptedException {
        Thread.sleep(1000);
        return "Hello Configuration Filter";
    }


    @GetMapping("/annotation")
    public String getAnotationFilter() throws InterruptedException {
        Thread.sleep(1000);
        return "Hello Annotation Filter";
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("One_MyFilterWithAnnotation","1");
        map.put("Two_MyFilterWithAnnotation","1");
        for(String key : map.keySet()){
            System.out.println(key);
        }
    }
}
