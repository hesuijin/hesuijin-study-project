package com.example.jwt.secuirty.serviceImpl;

import com.example.jwt.secuirty.request.LoginRequest;
import com.example.jwt.secuirty.service.AuthService;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public String createToken(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void removeToken() {

    }
}
