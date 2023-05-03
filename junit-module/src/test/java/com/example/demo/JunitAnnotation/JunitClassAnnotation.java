package com.example.demo.JunitAnnotation;

import com.example.demo.component.JunitComponent;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2023/5/4
 */

// 声明该类为Spring的单元测试类  被Spring管理 可以使用Autowire进行依赖注入
@SpringBootTest

//@RunWith时  建议必须有一个Junit4的@Test
//@RunWith(SpringRunner.class)
public class JunitClassAnnotation {

    @Autowired
    JunitComponent junitComponent;

    @Test
    public void junitAnnotationTest() {
        System.out.println("你好，单元测试! ");
    }
}
