package com.example.security.mvc.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.security.base.entity.User;
import com.example.security.base.request.LoginRequest;
import com.example.security.component.UserComponent;
import com.example.security.mvc.dao.UserMapper;
import com.example.security.mvc.service.AuthService;
import com.example.security.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/4
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final UserComponent userComponent;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public String createToken(LoginRequest loginRequest) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", loginRequest.getUserName());
        queryWrapper.last("limit 1");
        User user = userMapper.selectOne(queryWrapper);

        if(user == null){
            throw new BadCredentialsException("The user is not exist.");
        }

        //判断账号密码是否正确
        if (!userComponent.userCheck(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("The user name or password is not correct.");
        }
        //判断该账户是否被禁用
        Boolean isEnabled = user.getEnabled();
        if (!isEnabled) {
            throw new BadCredentialsException("User is forbidden to login");
        }

        //该账户名称对应的 角色
        List<String> authorities = user.getRoles()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        //生成token
        String token = JwtTokenUtils.createToken(user.getId().toString(), user.getUserName(), authorities, loginRequest.getRememberMe());
        //存储到redis中
        stringRedisTemplate.opsForValue().set(user.getId().toString(), token);
        return token;
    }

    @Override
    public void removeToken() {
        //获取用户ID  用于删除Redis中对应的值
        String userId = userComponent.getCurrentUser().getId().toString();
        stringRedisTemplate.delete(userId);
    }

//      // 构建jwtUser对象
//        JwtUser jwtUser = new JwtUser(user);
//        if (!jwtUser.isEnabled()) {
//            throw new BadCredentialsException("User is forbidden to login");
//        }
//        List<String> authorities = jwtUser.getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
}
