package com.example.demo.modules.oracleModule.base.request;

import lombok.Data;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@Data
public class MemberUpdateReq {


    /**
     * id  NUMBER(16)
     */
    private Long id;

    /**
     * name VARCHAR2(10)
     */
    private String name;

    /**
     * age VARCHAR2(4)
     */
    private String age;
}
