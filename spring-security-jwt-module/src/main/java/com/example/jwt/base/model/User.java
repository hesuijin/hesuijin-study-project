package com.example.jwt.base.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.jwt.base.BaseModel;
import com.example.jwt.base.info.UserNameInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
public class User extends BaseModel {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private Boolean enabled;

//    忽略该字段
    @TableField(exist = false)
    private List<UserRole> userRoles = new ArrayList<>();

    public List<SimpleGrantedAuthority> getRoles() {
        List<Role> roles = userRoles.stream().map(UserRole::getRole).collect(Collectors.toList());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    public UserNameInfo getUserNameInfo() {
        return UserNameInfo.builder().fullName(this.fullName)
                .userName(this.userName).build();
    }
}