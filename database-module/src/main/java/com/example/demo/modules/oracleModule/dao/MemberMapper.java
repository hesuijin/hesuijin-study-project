package com.example.demo.modules.oracleModule.dao;

import com.example.demo.modules.oracleModule.base.model.Member;
import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@Repository("MemberMapperOracle")
@Mapper
public interface MemberMapper {

    /**
     * 查询member表数据
     *
     * @return
     */
    List<Member> selectMemberList();


    void updateMemberByReq(MemberUpdateReq memberUpdateReq);
}
