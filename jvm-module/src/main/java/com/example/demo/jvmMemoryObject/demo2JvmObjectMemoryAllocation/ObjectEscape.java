package com.example.demo.jvmMemoryObject.demo2JvmObjectMemoryAllocation;

import com.example.demo.jvmMemoryObject.User;

/**
 * @Description: 对象逃逸
 * @Author HeSuiJin
 * @Date 2021/4/12
 */
public class ObjectEscape {

    public User test1() {
        //user 分配给堆
        User user = new User();
        user.setId(1L);
        user.setName("zhuge");
        //保存到数据库
        return user;
    }

    public void test2() {
        //user 分配给线程栈
        User user = new User();
        user.setId(1L);
        user.setName("zhuge");
        //保存到数据库
    }

}
