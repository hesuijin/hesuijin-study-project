package com.example.demo.dao;

import com.example.demo.base.model.Member;
import com.example.demo.base.request.UpdateMemberRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/3
 */
@Repository
@Mapper
public interface MemberMapper {

    /**
     * 查询member表数据
     *
     * @return
     */
    List<Member> selectMemberList();

    /**
     * 根据Id 查询Member
     * @param id
     * @return
     */
    Member selectMemberById(Long id);

    void updateMemberByReq(UpdateMemberRequest updateMemberRequest);
}
