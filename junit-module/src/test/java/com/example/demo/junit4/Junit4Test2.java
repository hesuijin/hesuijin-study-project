package com.example.demo.junit4;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2023/5/5
 */
@Slf4j
@SpringBootTest
public class Junit4Test2 {

    @Test
    public void junit4Test2() {

        log.info("junit4Test2 执行");
        log.info("junit4Test2 打印value：" + Junit4Test1.value);
    }
}
