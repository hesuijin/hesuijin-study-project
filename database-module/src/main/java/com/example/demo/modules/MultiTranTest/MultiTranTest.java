package com.example.demo.modules.MultiTranTest;

import com.example.demo.configMutiTransaction.MultiTransactional;
import com.example.demo.constant.TransactionConstant;
import com.example.demo.modules.mysqlModule.base.model.Member;
import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;
import com.example.demo.modules.oracleModule.dao.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multiTranTest")
@Slf4j
public class MultiTranTest {

    @Autowired
    private com.example.demo.modules.mysqlModule.dao.MemberMapper mysqlMemberMapper;

    @Autowired
    private MemberMapper oracleMemberMapper;

    @GetMapping("test1")
    @MultiTransactional(values = {TransactionConstant.DataSourceTransactionManager.ORACLE, TransactionConstant.DataSourceTransactionManager.MYSQL})
    public void MultiTransactionalTest3() {
        MemberUpdateReq memberUpdateReq = new MemberUpdateReq();
        memberUpdateReq.setId(1L);
        memberUpdateReq.setName("222222222");
        memberUpdateReq.setAge("999");
        oracleMemberMapper.updateMemberByReq(memberUpdateReq);
        Member member = new Member();

        int a = 1 / 0;
        member.setId(1L);
        member.setName("22222222");
        member.setAge(10);
        mysqlMemberMapper.updateMemberByReq(member);
    }

    @GetMapping("test2")
    @MultiTransactional(values = {TransactionConstant.DataSourceTransactionManager.ORACLE, TransactionConstant.DataSourceTransactionManager.MYSQL})
    public void MultiTransactionalTest4() {
        MemberUpdateReq memberUpdateReq = new MemberUpdateReq();
        memberUpdateReq.setId(1L);
        memberUpdateReq.setName("333333333");
        memberUpdateReq.setAge("999");
        oracleMemberMapper.updateMemberByReq(memberUpdateReq); Member member = new Member();
        int a = 1 / 0;
        member.setId(1L);
        member.setName("33333333");
        member.setAge(10);
        mysqlMemberMapper.updateMemberByReq(member);
    }

}
