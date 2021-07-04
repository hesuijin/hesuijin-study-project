package com.example.demo.modules.oracleModule;

import com.example.demo.modules.oracleModule.base.model.Member;
import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;
import com.example.demo.modules.oracleModule.controller.OracleController;
import com.example.demo.modules.oracleModule.controller.TransactionRollbackForController;
import com.example.demo.modules.oracleModule.service.impl.OracleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OracleServiceImplTest{

    @Autowired
    private OracleServiceImpl oracleService;

    @Autowired
    private TransactionRollbackForController transactionRollbackForController;

    @Test
//    @Transactional(rollbackFor = RuntimeException.class)
    // 必须指定oracleTransactionManager 事务
    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    @Rollback
    public void oracleSelectTest() {
        MemberUpdateReq memberUpdateReq = new MemberUpdateReq();
        memberUpdateReq.setId(1L);
        memberUpdateReq.setName("111");
        memberUpdateReq.setAge("102");
        transactionRollbackForController.updateReturnTestJunit(memberUpdateReq);
    }


    @Test
    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    @Rollback
    public void oracleSelectTest1() {
        MemberUpdateReq memberUpdateReq = new MemberUpdateReq();
        memberUpdateReq.setId(1L);
        memberUpdateReq.setName("111");
        memberUpdateReq.setAge("199");
        transactionRollbackForController.updateReturnTest2(memberUpdateReq);
    }
}
