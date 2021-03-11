package com.example.jwt.component;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jwt.base.model.UserRole;
import com.example.jwt.secuirty.dao.UserRoleMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


/**
 * user通用逻辑组件
 * @Author HeSuiJin
 * @Date 2021/3/10 11:36
 * @Description:
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserComponent {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleMapper userRoleMapper;


    /**
     * 检查账号密码是对应
     * @param currentPassword
     * @param password
     * @return
     */
    public boolean userCheck(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }

    /**
     * 根据用户名称获取角色名称
     * @param userName
     * @return
     */
    public List<SimpleGrantedAuthority> getRoleNameByUserName(String userName) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.select("name");
        List<UserRole> userRoles = userRoleMapper.selectList(queryWrapper);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }
}
