package com.example.jwt.secuirty.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jwt.base.model.Role;
import com.example.jwt.base.model.User;
import com.example.jwt.base.model.UserRole;
import com.example.jwt.base.request.UserRegisterRequest;
import com.example.jwt.common.enums.RoleType;
import com.example.jwt.exception.RoleNotFoundException;
import com.example.jwt.exception.UserNameAlreadyExistException;
import com.example.jwt.secuirty.dao.RoleMapper;
import com.example.jwt.secuirty.dao.UserMapper;
import com.example.jwt.secuirty.dao.UserRoleMapper;
import com.example.jwt.secuirty.service.UserService;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 17:27
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserRegisterRequest userRegisterRequest) {
        //校验该用户是否存在
        ensureUserNameNotExist(userRegisterRequest.getUserName());
        User user = userRegisterRequest.getUser();
        //设置密码加密
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));

        userMapper.insert(user);

        //给用户绑定两个角色：用户和管理者

        log.info("打印：{}",RoleType.USER.getName());
         Role studentRole = roleMapper.findByRoleName(RoleType.USER.getName());
         log.info(JSONObject.toJSONString(studentRole));
         boolean studentRoleExist = studentRole != null;
         if(!studentRoleExist){
             new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getName()));
         }
        Role managerRole = roleMapper.findByRoleName(RoleType.MANAGER.getName());
        boolean managerRoleExist = managerRole != null;
        if(!managerRoleExist){
            new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.MANAGER.getName()));
        }
        userRoleMapper.insert(new UserRole(user,studentRole));
        userRoleMapper.insert(new UserRole(user,managerRole));

    }



    /**
     * 删除用户
     * @param userName
     */
    public void delete(String userName) {
        boolean exist = findUserByUserName(userName);
        if (!exist) {
            throw new UserNameAlreadyExistException(ImmutableMap.of("username:", userName));
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        userMapper.delete(queryWrapper);
    }

    /**
     * 查询该该账户是否存在
     * @param userName
     */
    private void ensureUserNameNotExist(String userName) {
        boolean exist = findUserByUserName(userName);
        if (exist) {
            throw new UserNameAlreadyExistException(ImmutableMap.of("username:", userName));
        }
    }

    /**
     * 根据UserName查询用户是否存在
     * @param userName
     * @return
     */
    private Boolean findUserByUserName(String userName){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        queryWrapper.last("limit 1");
        User user = userMapper.selectOne(queryWrapper);
        //存在则为true 不存在为false
        return user != null;
    }

}