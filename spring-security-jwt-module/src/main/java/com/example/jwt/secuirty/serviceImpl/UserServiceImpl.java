package com.example.jwt.secuirty.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.jwt.base.model.Role;
import com.example.jwt.base.model.User;
import com.example.jwt.base.model.UserRole;
import com.example.jwt.base.request.UserRegisterRequest;
import com.example.jwt.common.enums.RoleType;
import com.example.jwt.component.UserComponent;
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
    private final UserComponent userComponent;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserRegisterRequest userRegisterRequest) {
        //校验该用户是否存在
        ensureUserNameNotExist(userRegisterRequest.getUserName());
        User user = userRegisterRequest.getUser();
        log.info("打印user：{}", JSONObject.toJSONString(user));
        //设置密码加密
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
        userMapper.insert(user);

        //给用户绑定两个角色：用户和管理者
        Role studentRole = roleMapper.findByRoleName(RoleType.USER.getName());
        boolean studentRoleExist = studentRole != null;
        if (!studentRoleExist) {
            new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.USER.getName()));
        }
        Role managerRole = roleMapper.findByRoleName(RoleType.MANAGER.getName());

        boolean managerRoleExist = managerRole != null;
        if (!managerRoleExist) {
            new RoleNotFoundException(ImmutableMap.of("roleName", RoleType.MANAGER.getName()));
        }

        userRoleMapper.insert(createUserRoleStudent(user, studentRole));
        userRoleMapper.insert(createUserRoleManager(user, managerRole));
    }

    /**
     * 创建 UserRoleStudent对象
     *
     * @param user
     * @param studentRole
     * @return
     */
    private UserRole createUserRoleStudent(User user, Role studentRole) {
        UserRole userRoleStudent = UserRole.builder()
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .password(user.getPassword())
                .enabled(user.getEnabled())
                .name(studentRole.getName())
                .fullName(studentRole.getDescription())
                .build();
        return userRoleStudent;
    }

    /**
     * 创建 UserRoleManager对象
     *
     * @param user
     * @param managerRole
     * @return
     */
    private UserRole createUserRoleManager(User user, Role managerRole) {
        UserRole userRoleManager = UserRole.builder()
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .password(user.getPassword())
                .enabled(user.getEnabled())
                .name(managerRole.getName())
                .fullName(managerRole.getDescription())
                .build();
        return userRoleManager;
    }

//    /**
//     * 删除用户
//     *
//     * @param userName
//     */
//    public void delete(String userName) {
//        User user = findUserByUserName(userName);
//        if (user == null) {
//            throw new UserNameAlreadyExistException(ImmutableMap.of("username:", userName));
//        }
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name", userName);
//        userMapper.delete(queryWrapper);
//    }

    /**
     * 查询该该账户是否存在
     *
     * @param userName
     */
    private void ensureUserNameNotExist(String userName) {
        User user = userComponent.findUserByUserName(userName);
        if (user != null) {
            throw new UserNameAlreadyExistException(ImmutableMap.of("username:", userName));
        }
    }



}
