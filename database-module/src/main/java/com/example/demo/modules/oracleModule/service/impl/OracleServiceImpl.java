package com.example.demo.modules.oracleModule.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.modules.oracleModule.base.model.Member;
import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;
import com.example.demo.modules.oracleModule.dao.MemberMapper;
import com.example.demo.modules.oracleModule.service.OracleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@Service
@Slf4j
public class OracleServiceImpl implements OracleService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Member> selectTest() {
        List<Member> memberList = memberMapper.selectMemberList();
        log.info("打印memberList{}", JSONObject.toJSONString(memberList));
        return null;
    }

    //rollbackFor  Transactional的事务回滚标志  当Transactional检查到 对应的 Exception.class 异常时会回滚
    // Exception
    // 非运行期异常 IOException  SQLException （checked exceptions 可查异常）  必须显式捕获  否则编译期不会通过
    // 运行期异常   NullPointerException (unchecked exceptions 不可查异常)  运行中逻辑错误引起的  可以对有可能发生异常的位置进行捕获
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateTest(MemberUpdateReq memberUpdateReq) {
        memberMapper.updateMemberByReq(memberUpdateReq);


    }

//    https://blog.csdn.net/Mint6/article/details/78363761


//    @Transactional(rollbackFor = RuntimeException.class)
//    public void updateTest2(MemberUpdateReq memberUpdateReq) {
//        memberMapper.updateMemberByReq(memberUpdateReq);
//    }
//
//    @Transactional(rollbackFor = RuntimeException.class)
//    public void updateTest2(MemberUpdateReq memberUpdateReq) {
//        memberMapper.updateMemberByReq(memberUpdateReq);
//    }

}
