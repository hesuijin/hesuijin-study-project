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
public class Junit4Test1 {

    static String value = "10";

    @Test
    public void junit4Test1() {
        log.info("junit4Test1 执行");
        log.info("junit4Test1 打印value：" + Junit4Test1.value);
        Junit4Test1.value = "100";
        log.info("junit4Test1 修改value：" + Junit4Test1.value);
    }

}
