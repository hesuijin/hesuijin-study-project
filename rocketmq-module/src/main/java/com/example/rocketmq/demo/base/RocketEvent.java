package com.example.rocketmq.demo.base;

import lombok.Data;

/**
 * @Author HeSuiJin
 * @Date 2021/3/18 22:18
 * @Description:
 */
@Data
public class RocketEvent<T> {

    /**
     * 事件
     */
    private String event;
    /**
     * 信息
     */
    private T data;
    /**
     * 二级主题
     */
    private String tag;

    public RocketEvent(String event, T data,String tag) {
        this.event = event;
        this.data = data;
        this.tag = tag;

    }

    public RocketEvent(String event, T data) {
        this.event = event;
        this.data = data;
    }
}
