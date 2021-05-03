package com.example.demo.copyUtils;

import lombok.Data;

import java.util.ArrayList;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/5/3
 */
//注意 不要重写toString方法
//否则 在打印对象的时候会默认使用toString
public class UserDTO {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public ArrayList getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    private Long id;

    private String string ;

    ArrayList arrayList ;

}
