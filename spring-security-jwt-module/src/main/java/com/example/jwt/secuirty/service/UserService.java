package com.example.jwt.secuirty.service;

import com.example.jwt.base.request.UserRegisterRequest;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 17:27
 * @Description:
 */
public interface UserService {

    void save(UserRegisterRequest userRegisterRequest);
}
