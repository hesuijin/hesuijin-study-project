package com.example.demo.dataCreataTion;

import com.example.demo.model.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/6
 */
@Component
public class CreateMemberList {

    public List<Member> methodArray(){
        List<Member> memberList = new ArrayList<>();

        Member member1 = new Member();
        member1.setId(101L);
        member1.setName("用户名称1");
        member1.setAge(15);

        Member member2 = new Member();
        member2.setId(102L);
        member2.setName("用户名称2");
        member2.setAge(18);

        Member member3 = new Member();
        member3.setId(103L);
        member3.setName("用户名称3");
        member3.setAge(20);

        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);

        return memberList;
    }
}
