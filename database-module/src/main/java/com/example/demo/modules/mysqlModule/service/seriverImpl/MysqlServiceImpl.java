package com.example.demo.modules.mysqlModule.service.seriverImpl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.modules.mysqlModule.base.model.Member;
import com.example.demo.modules.mysqlModule.dao.MemberMapper;
import com.example.demo.modules.mysqlModule.service.MysqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/2
 */
@Service
@Slf4j
public class MysqlServiceImpl implements MysqlService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Member> mysqlSelectTest() {
        List<Member> memberList =  memberMapper.selectMemberList();

        log.info("打印memberList{}", JSONObject.toJSONString(memberList));

        return memberList;
    }
}
