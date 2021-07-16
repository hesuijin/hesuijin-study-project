package com.example.demo.modules.multiDataSourceTranaction;

import com.example.demo.configMutiTransaction.MultiTransactional;
import com.example.demo.constant.TransactionConstant;
import com.example.demo.modules.mysqlModule.base.model.Member;
import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;
import com.example.demo.modules.oracleModule.dao.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/5
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProgrammaticTransaction {

    @Autowired
    private com.example.demo.modules.mysqlModule.dao.MemberMapper mysqlMemberMapper;

    @Autowired
    private MemberMapper oracleMemberMapper;


    @Test
    @Transactional(transactionManager = TransactionConstant.DataSourceTransactionManager.MYSQL, rollbackFor = RuntimeException.class)
    public void mysqlTransactionJunit() {
        Member member = new Member();
        member.setId(1L);
        member.setName("hhhh");
        member.setAge(10);
        mysqlMemberMapper.updateMemberByReq(member);
        int a = 1 / 0;
    }



    @Test
    @Transactional(transactionManager = TransactionConstant.DataSourceTransactionManager.ORACLE, rollbackFor = RuntimeException.class)
    public void oracleTransactionJunit() {
        MemberUpdateReq memberUpdateReq = new MemberUpdateReq();
        memberUpdateReq.setId(1L);
        memberUpdateReq.setName("hhhh");
        memberUpdateReq.setAge("999");
        oracleMemberMapper.updateMemberByReq(memberUpdateReq);
//        int a = 1 / 0;
    }

    @Autowired
    @Qualifier("mysqlTransactionManager")
    private DataSourceTransactionManager mysqlDataSourceTransactionManager;

    @Autowired
    @Qualifier("oracleTransactionManager")
    private DataSourceTransactionManager oracelDataSourceTransactionManager;

    @Test
//    @Transactional(transactionManager =TranactionConstant.DataSourceTransactionManager.ORACLE,rollbackFor = RuntimeException.class)
    public void multiTransactionJunitMysqlFlase() {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus mysqlStatus = oracelDataSourceTransactionManager.getTransaction(defaultTransactionDefinition);

        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("hhhh");
            member.setAge(10);
            mysqlMemberMapper.updateMemberByReq(member);
            int a = 1 / 0;
            oracelDataSourceTransactionManager.commit(mysqlStatus);
        } catch (Exception e) {
            oracelDataSourceTransactionManager.rollback(mysqlStatus);
            System.out.println("我是错误");
        }
    }

    @Test
//    @Transactional(transactionManager =TranactionConstant.DataSourceTransactionManager.ORACLE,rollbackFor = RuntimeException.class)
    public void multiTransactionJunitMysqlTrue() {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus mysqlStatus = mysqlDataSourceTransactionManager.getTransaction(defaultTransactionDefinition);

        try {
            Member member = new Member();
            member.setId(1L);
            member.setName("hhhhhhh");
            member.setAge(10);
            mysqlMemberMapper.updateMemberByReq(member);
            int a = 1 / 0;
            mysqlDataSourceTransactionManager.commit(mysqlStatus);
        } catch (Exception e) {
            mysqlDataSourceTransactionManager.rollback(mysqlStatus);
            System.out.println("我是错误");
        }
    }

    @Test
    @MultiTransactional(values = {TransactionConstant.DataSourceTransactionManager.ORACLE, TransactionConstant.DataSourceTransactionManager.MYSQL})
    public void MultiTransactionalTest() {
        MemberUpdateReq memberUpdateReq = new MemberUpdateReq();
        memberUpdateReq.setId(1L);
        memberUpdateReq.setName("9999999999");
        memberUpdateReq.setAge("999");
        oracleMemberMapper.updateMemberByReq(memberUpdateReq);Member member = new Member();

        int a = 1 / 0;
        member.setId(1L);
        member.setName("999999999999999");
        member.setAge(10);
        mysqlMemberMapper.updateMemberByReq(member);
    }

    @Test
    @MultiTransactional(values = {TransactionConstant.DataSourceTransactionManager.ORACLE, TransactionConstant.DataSourceTransactionManager.MYSQL})
    public void MultiTransactionalTest2() {
        MemberUpdateReq memberUpdateReq = new MemberUpdateReq();
        memberUpdateReq.setId(1L);
        memberUpdateReq.setName("gggg");
        memberUpdateReq.setAge("999");
        oracleMemberMapper.updateMemberByReq(memberUpdateReq); Member member = new Member();
        int a = 1 / 0;
        member.setId(1L);
        member.setName("gggg");
        member.setAge(10);
        mysqlMemberMapper.updateMemberByReq(member);
    }

    @Test
    @MultiTransactional(values = {TransactionConstant.DataSourceTransactionManager.ORACLE, TransactionConstant.DataSourceTransactionManager.MYSQL})
    public void MultiTransactionalTest3() {
        MemberUpdateReq memberUpdateReq = new MemberUpdateReq();
        memberUpdateReq.setId(1L);
        memberUpdateReq.setName("111111111");
        memberUpdateReq.setAge("999");
        oracleMemberMapper.updateMemberByReq(memberUpdateReq);Member member = new Member();

        int a = 1 / 0;
        member.setId(1L);
        member.setName("11111111111111");
        member.setAge(10);
        mysqlMemberMapper.updateMemberByReq(member);
    }

    @Test
    @MultiTransactional(values = {TransactionConstant.DataSourceTransactionManager.ORACLE, TransactionConstant.DataSourceTransactionManager.MYSQL})
    public void MultiTransactionalTest4() {
        MemberUpdateReq memberUpdateReq = new MemberUpdateReq();
        memberUpdateReq.setId(1L);
        memberUpdateReq.setName("111111111");
        memberUpdateReq.setAge("999");
        oracleMemberMapper.updateMemberByReq(memberUpdateReq); Member member = new Member();
        int a = 1 / 0;
        member.setId(1L);
        member.setName("111111111");
        member.setAge(10);
        mysqlMemberMapper.updateMemberByReq(member);
    }
}
