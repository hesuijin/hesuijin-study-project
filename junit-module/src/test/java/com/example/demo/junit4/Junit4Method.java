package com.example.demo.junit4;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.model.Member;
import com.example.demo.base.request.UpdateMemberRequest;
import com.example.demo.dao.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: Junit4 断言测试
 * @Author HeSuiJin
 * @Date 2023/5/5
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class Junit4Method {

    @Autowired
    private MemberMapper memberMapper;

    @Before
    public void junit4Before() {
        log.info("开始Junit4单元测试");
    }

    /**
     * 断言中如果失败 会直接报错返回 在Junit的窗口测试将中止
     */
    @Test
    public void junit4Assert() {

        String obj1 = "junit";
        String obj2 = "junit";
        String obj3 = "test";
        String obj4 = "test";
        String obj5 = null;
        int[] arithmetic1 = {1, 2, 3};
        int[] arithmetic2 = {1, 2, 3};

//        assertEquals() 如果比较的两个值是相等的，此方法将正常返回；。
        Assert.assertEquals("两个对象是相等",obj1, obj2);
//        assertSame()    两个对象引用 是指向完全相同的对象
        Assert.assertSame("是指向完全相同的对象",obj3, obj4);
//        assertNotSame() 两个对象引用 不是指向完全相同的对象
        Assert.assertNotSame("不是指向完全相同的对象",obj2, obj4);
//        assertNotNull 一个对象不为空
        Assert.assertNotNull("对象不为空",obj2);
//        assertNull 一个对象为空
        Assert.assertNull("对象为空",obj5);

        Assert.assertTrue("断言条件为true", true);
        Assert.assertFalse("断言条件为false", false);

        Assert.assertArrayEquals("断言数组相同",arithmetic1, arithmetic2);
    }

    //突然不想执行该单元测试是 直接使用该注解忽略
    @Ignore("该单元测试不执行")
    @Test
    public void junit4Ignore() {
        Assert.assertEquals("两个对象是相等",10, 100);
    }

    //设置单元测试的超时时间
    //超时则异常 org.junit.runners.model.TestTimedOutException: test timed out after 10000 milliseconds
    @Test(timeout = 10000)
    public void junit4Timeout() {
        try {
            Thread.sleep(10001);
        } catch (InterruptedException e) {
           log.error("线程睡眠失败:"+e.getMessage());
        }
    }


    //打开的话测试执行完后 数据库事务会可自动回滚
    @Rollback(true)
    //开启事务 异常时回滚事务
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void junit4Rollback() {

        Member member = memberMapper.selectMemberById(1L);
        log.info("打印获取的数据:{}", JSONObject.toJSON(member));

        UpdateMemberRequest updateMemberRequest = new UpdateMemberRequest();
        updateMemberRequest.setId(member.getId());
        updateMemberRequest.setName("HSJ");
        updateMemberRequest.setAge("100");
        memberMapper.updateMemberByReq(updateMemberRequest);

        log.info("事务回滚");
    }

    @After
    public void junit4After() {
        log.info("结束Junit4单元测试");
    }

}


