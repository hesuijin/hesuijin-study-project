package com.example.demo.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author HeSuiJin
 * @Date 2021/3/17 20:25
 * @Description:
 */
@Component
@Slf4j
@EnableAsync
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * fixedRate：固定速率执行。每5秒执行一次。
     */
//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTimeWithFixedRate() {
        log.info("固定速率执行  Current Thread : {}", Thread.currentThread().getName());
        log.info("固定速率执行 : The time is now {}", dateFormat.format(new Date()));
    }

    /**
     * fixedDelay：固定延迟执行。距离上一次调用成功后2秒才执。
     */
//    @Scheduled(fixedDelay = 2000)
    @Async
    public void reportCurrentTimeWithFixedDelay() {
        try {
            TimeUnit.SECONDS.sleep(3);
            log.info("固定延迟执行 Current Thread : {}", Thread.currentThread().getName());
            log.info("固定延迟执行 : The time is now {}", dateFormat.format(new Date()));
        } catch (InterruptedException e) {
            log.info("定时任务异常" + e.getMessage(), e);
        }
    }


    private List<Integer> index = Arrays.asList(6, 6, 2, 3);
    int i = 0;

    /**
     * 固定执行存在的问题
     */
//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTimeWithFixedRateProblem() {
        if (i == 0) {
            log.info("Start time is {}", dateFormat.format(new Date()));
        }
        if (i < 4) {
            try {
                TimeUnit.SECONDS.sleep(index.get(i));
                log.info("固定延迟执行 Current Thread : {}", Thread.currentThread().getName());
                log.info("固定执行 问题 : The time is now {}", dateFormat.format(new Date()));
            } catch (InterruptedException e) {
                log.info("定时任务异常" + e.getMessage(), e);
            }
            i++;
        }
    }

    /**
     * crond的事
     */
    @Scheduled(cron = "  *  * * * * ?")
//    @Async
    public void cronTest() {
        log.info("cronTest Current Thread : {}", Thread.currentThread().getName());
        log.info("cronTest : The time is now {}", dateFormat.format(new Date()));

    }
}
