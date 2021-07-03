package com.example.demo.modules.mysqlModule.controller;


import com.example.demo.modules.mysqlModule.base.model.Member;
import com.example.demo.modules.mysqlModule.service.MysqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/2
 */
@RestController
@RequestMapping("/mysql")
@Slf4j
public class MysqlController {

    @Autowired
    private MysqlService mysqlService;


    private void mysqlSelectTest(){
       List<Member> memberList = mysqlService.mysqlSelectTest();
    }

}
