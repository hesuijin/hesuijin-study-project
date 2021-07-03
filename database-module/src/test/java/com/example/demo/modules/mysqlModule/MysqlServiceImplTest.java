package com.example.demo.modules.mysqlModule;

import com.example.demo.modules.mysqlModule.base.model.Member;
import com.example.demo.modules.mysqlModule.service.seriverImpl.MysqlServiceImpl;
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
 * @Date 2021/7/2
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MysqlServiceImplTest {

    @Autowired
    private MysqlServiceImpl mysqlService;

    @Test
    public void mysqlSelectTest() {
       List<Member> memberList = mysqlService.mysqlSelectTest();
    }
}
