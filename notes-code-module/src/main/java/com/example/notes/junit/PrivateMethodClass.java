package com.example.notes.junit;

import com.alibaba.fastjson.JSONObject;
import com.example.notes.model.base.request.UpdateMemberRequest;
import com.example.notes.model.base.response.UpdateMemberResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/11
 */
@Service
@Slf4j
public class PrivateMethodClass {

    /**
     * 公共方法 publicMethodUpdateMember
     */
    public UpdateMemberResponse publicMethodUpdateMember(UpdateMemberRequest updateMemberRequest) {
        log.info("我是publicMethodUpdateMember的入参啊：{}", JSONObject.toJSONString(updateMemberRequest.toString()));
        UpdateMemberResponse updateMemberResponse = new UpdateMemberResponse();
        updateMemberResponse.setId(1L);
        updateMemberResponse.setName("HeSuiJin");
        updateMemberResponse.setAge(updateMemberRequest.getAge()+10);
        return updateMemberResponse;
    }


    /**
     * 私有方法
     */
    private void privateMethodVoid() {
        log.info("privateMethodVoid被调用");
    }

    /**
     * String私有方法
     */
    private String privateMethodString(String request) {
        log.info("我是privateMethodString的入参啊：{}", request);
        return request;
    }

    /**
     * Integer私有方法
     */
    private Integer privateMethodInteger(Integer request) {
        log.info("我是privateMethodInteger的入参啊：{}", request);
        return request;
    }


    /**
     * 私有方法   string + integer
     */
    private String privateMethod(String name, Integer age) {
        log.info("我是privateMethod的入参啊 name：{},age: {}", name, age);
        return "名称：" + name + "年龄：" + age;
    }

    /**
     * 私有方法  修改privateMethodUpdateMember
     */
    private UpdateMemberRequest privateMethodUpdateMember(UpdateMemberRequest updateMemberRequest) {
        log.info("我是privateMethodUpdateMember的入参啊：{}", JSONObject.toJSONString(updateMemberRequest.toString()));
        updateMemberRequest.setId(1L);
        updateMemberRequest.setName("HeSuiJin");
        updateMemberRequest.setAge(18);
        return updateMemberRequest;
    }
}

