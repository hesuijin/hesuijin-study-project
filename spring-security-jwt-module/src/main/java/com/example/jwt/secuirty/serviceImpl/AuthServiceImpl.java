package com.example.jwt.secuirty.serviceImpl;

import com.example.jwt.base.request.LoginRequest;
import com.example.jwt.secuirty.dao.UserMapper;
import com.example.jwt.secuirty.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/4
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String createToken(LoginRequest loginRequest) {

//        userMapper.
        return null;
    }

    @Override
    public void removeToken() {

    }
}
