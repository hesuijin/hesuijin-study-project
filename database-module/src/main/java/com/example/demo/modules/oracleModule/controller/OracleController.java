package com.example.demo.modules.oracleModule.controller;

import com.example.demo.modules.oracleModule.base.model.Member;
import com.example.demo.modules.oracleModule.service.OracleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@RestController
@RequestMapping("/oracle")
@Slf4j
public class OracleController {

    @Autowired
    private OracleService oracleService;

    @GetMapping("select")
    public void  oraclelSelectTest(){
        List<Member> memberList = oracleService.oraclelSelectTest();
    }

}
