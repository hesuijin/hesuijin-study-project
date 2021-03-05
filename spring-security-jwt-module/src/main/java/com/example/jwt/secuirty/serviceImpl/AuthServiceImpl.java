package com.example.jwt.secuirty.serviceImpl;

import com.example.jwt.base.request.LoginRequest;
import com.example.jwt.secuirty.dao.UserMapper;
import com.example.jwt.secuirty.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("user_name",loginRequest.getUsername());
        System.out.println(userMapper.selectByMap(columnMap));
        return null;
    }

    @Override
    public void removeToken() {

    }
}
