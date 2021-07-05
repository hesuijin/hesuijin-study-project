package com.example.demo.modules.mysqlModule.dao;

import com.example.demo.modules.mysqlModule.base.model.Member;
import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@Repository("MemberMapperMysql")
@Mapper
public interface MemberMapper {

    /**
     * 查询member表数据
     *
     * @return
     */
    List<Member> selectMemberList();

    void updateMemberByReq(Member member);

}
