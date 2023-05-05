package com.example.demo.junit4;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2023/5/5
 */
@Slf4j
//@RunWith(SpringRunner.class) 执行本类中的@Test
//@RunWith(SpringRunner.class)

//@RunWith(Suite.class) 执行注解中的Junit4类中Test
@RunWith(Suite.class)
@Suite.SuiteClasses({ Junit4Test1.class, Junit4Test2.class })
public class Junit4Suite {

    @Test
    public void suiteTest() {
        log.info("suiteTest 执行");
    }
}
