package com.example.demo.controller;

import com.example.demo.RedisDistributedLock.RedisDistributedLockComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Description: 下单测试
 * @Author HeSuiJin
 * @Date 2021/4/12
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class RedisDistributedLockController {

    @Autowired
    private RedisDistributedLockComponent redisDistributedLockComponent;

    @PostMapping(value = "/member/create")
    public String orderPay(Long memberId) {

        String memberOrderLockKey = "member:createOrder:" + memberId;
        //该锁会锁住这个下单的用户ID5秒
        boolean memberLock = redisDistributedLockComponent.getLock(memberOrderLockKey, 2000L, 5000L);

        //如果memberLock 为 false 则失败
        if (!memberLock) {
            return "fail  请不要重复下单";
        }
        try {
            //下单耗时3秒操作

            System.out.println(new Date());
            Thread.sleep(3000L);
            System.out.println(new Date());

        } catch (Exception e) {
            log.error("创建预授权订单，用户ID：{}，异常：{}", memberId, e.getMessage(), e);
        } finally {
            boolean isRelease = redisDistributedLockComponent.releaseLock(memberOrderLockKey);
            if (!isRelease) {
                log.error("该key的分布式锁释放失败{}", memberOrderLockKey);
            }
        }

        return "success  下单成功";
    }


}
