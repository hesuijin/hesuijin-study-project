package com.example.jwt.secuirty.service;

import com.example.jwt.base.request.LoginRequest;

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/4
 */
public interface AuthService {

    /**
     * 创建token
     * @param loginRequest
     * @return
     */
    String createToken(LoginRequest loginRequest);

    /**
     * 删除token
     */
    void removeToken();
}
