package com.example.demo.modules.oracleModule.service;

import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;
import com.example.demo.constant.Result;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/4
 */
public interface TransactionRollbackForService {

    void updateMemberByReq1(MemberUpdateReq memberUpdateReq);
    
    void updateMemberByReq2(MemberUpdateReq memberUpdateReq);

    void updateMemberByReq3(MemberUpdateReq memberUpdateReq);

    void updateMemberByReq4(MemberUpdateReq memberUpdateReq);

    void updateMemberByReq5(MemberUpdateReq memberUpdateReq);
    
    String updateMemberByReqReturn1(MemberUpdateReq memberUpdateReq);

    String updateMemberByReqReturn2(MemberUpdateReq memberUpdateReq);

    String updateReturnTestOver(MemberUpdateReq memberUpdateReq, String returnString);

    String updateReturnTestOver2(MemberUpdateReq memberUpdateReq, String returnString);

    Result updateReturnTestOver3(MemberUpdateReq memberUpdateReq, Result result );

    Result updateReturnTestJunit(MemberUpdateReq memberUpdateReq, Result result);
}
