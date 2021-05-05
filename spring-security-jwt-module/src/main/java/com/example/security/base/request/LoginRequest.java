package com.example.security.base.request;

import lombok.Data;


/**
 * @author shuang.kou
 * @description 用户登录请求DTO
 */
@Data
public class LoginRequest {
    private String userName;
    private String password;
    private Boolean rememberMe = false;


}
