package com.example.demo.modules.oracleModule;

import com.example.demo.modules.oracleModule.base.model.Member;
import com.example.demo.modules.oracleModule.service.impl.OracleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OracleServiceImplTest{

    @Autowired
    private OracleServiceImpl oracleService;

    @Test
    public void mysqlSelectTest() {
        List<Member> memberList = oracleService.oraclelSelectTest();
    }
}
