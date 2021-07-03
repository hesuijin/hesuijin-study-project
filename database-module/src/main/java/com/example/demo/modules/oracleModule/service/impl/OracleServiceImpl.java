package com.example.demo.modules.oracleModule.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.modules.oracleModule.base.model.Member;
import com.example.demo.modules.oracleModule.dao.MemberMapper;
import com.example.demo.modules.oracleModule.service.OracleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Member> oraclelSelectTest() {
        List<Member> memberList = memberMapper.selectMemberList();
        log.info("打印memberList{}", JSONObject.toJSONString(memberList));
        return null;
    }
}
