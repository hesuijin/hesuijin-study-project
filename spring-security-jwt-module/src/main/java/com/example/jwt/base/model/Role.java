package com.example.jwt.base.model;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.jwt.base.BaseModel;
import com.example.jwt.component.UserComponent;
import com.example.jwt.secuirty.dao.UserRoleMapper;
import com.example.jwt.system.context.ApplicationContextHelper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 主键ID：role
 * @author hsj 2021-03-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Role extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String description;

//
//    public void test() throws Exception {
//        Method mehtod;
//        Class componentClass = UserComponent.class;
//        Object obj = ApplicationContextHelper.popBean(componentClass);
//        mehtod = componentClass.getMethod("getRoleNameByUserName", String.class);
//        Object a = mehtod.invoke(obj, "root");
//        List<SimpleGrantedAuthority> simpleGrantedAuthorities = (List<SimpleGrantedAuthority>) a;
//        log.info(JSONObject.toJSONString(a));
//        log.info(JSONObject.toJSONString(a));
//        log.info(JSONObject.toJSONString(simpleGrantedAuthorities));
//    }
}