package com.example.jwt.base.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jwt.base.BaseModel;
import com.example.jwt.base.info.UserNameInfo;
import com.example.jwt.component.UserComponent;
import com.example.jwt.secuirty.dao.UserMapper;
import com.example.jwt.secuirty.dao.UserRoleMapper;
import com.example.jwt.system.context.ApplicationContextHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 使用充血模型
 * 主键Id：user
 * @author hsj 2021-03-04
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Controller
public class User extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private Boolean enabled;


//    @TableField(exist = false)
//    否则使用mybatisPlus的时候 会把该属性 作为sql  select 的字段植而报错
//    @Autowired
//    new出来的对象 无法直接引用spring容器中的对象
//    private UserRoleMapper userRoleMapper;


    //使用JSON 转换打印时过滤掉该get方法
    @JSONField(serialize = false)
    public List<SimpleGrantedAuthority> getRoles()  {
        Method mehtod;
        Class componentClass = UserComponent.class;
//        反射出来的类 使用构造方法创建的对象也无法引用
//        Object obj = componentClass.getConstructor().newInstance();
        Object obj = ApplicationContextHelper.popBean(componentClass);
        Object objInvoke = new Object();
        try {
            mehtod = componentClass.getMethod("getRoleNameByUserName", String.class);
             objInvoke = mehtod.invoke(obj, this.userName);
        }catch (Exception e){
            log.info("User实体类反射异常： "+e.getMessage(),e);
        }
        return  (List<SimpleGrantedAuthority>) objInvoke;
    }

    @JSONField(serialize = false)
    public UserNameInfo getUserNameInfo() {
        return UserNameInfo.builder().fullName(this.fullName)
                .userName(this.userName).build();
    }
}