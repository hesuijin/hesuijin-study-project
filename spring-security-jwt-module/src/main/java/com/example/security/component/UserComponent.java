package com.example.security.component;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.security.base.entity.User;
import com.example.security.base.entity.UserRole;
import com.example.security.exception.UserNameNotFoundException;
import com.example.security.mvc.dao.UserMapper;
import com.example.security.mvc.dao.UserRoleMapper;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * user通用逻辑组件
 * @Author HeSuiJin
 * @Date 2021/3/10 11:36
 * @Description:
 */
@Component
@Scope("prototype")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserComponent {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRoleMapper userRoleMapper;
    private final UserMapper userMapper;

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

    /**
     * 根据UserName查询用户是否存在
     *
     * @param userName
     * @return
     */
    public User findUserByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.last("limit 1");
        User user = userMapper.selectOne(queryWrapper);
        //存在则为true 不存在为false
        return user;
    }

    /**
     * 根据上下文获取 User
     * @return
     */
    public User getCurrentUser() {
        String userName = getCurrentUserName();
        User user =  findUserByUserName(userName);
        if(user == null){
         throw new UserNameNotFoundException(ImmutableMap.of( "username:", userName));
        }
        return  findUserByUserName(getCurrentUserName());
    }

    private  String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
