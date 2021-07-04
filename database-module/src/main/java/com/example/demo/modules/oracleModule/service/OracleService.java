package com.example.demo.modules.oracleModule.service;

import com.example.demo.modules.oracleModule.base.model.Member;
import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
public interface OracleService {

    List<Member> selectTest();

    void updateTest(MemberUpdateReq memberUpdateReq);
}
