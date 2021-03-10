package com.example.jwt.secuirty.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.jwt.base.request.LoginRequest;
import com.example.jwt.common.constants.SecurityConstants;
import com.example.jwt.secuirty.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//private final AuthService authService;
//等价于
//@Autowired
// private AuthService authService;

//@AllArgsConstructor的作用

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/4
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Api(tags = "认证")
@Slf4j
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    @ApiOperation("登录")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        log.info("登录 请求入参 /auth/login ：{} ",JSONObject.toJSONString(loginRequest));
        String token = authService.createToken(loginRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(SecurityConstants.TOKEN_HEADER, token);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    @ApiOperation("退出")
    public ResponseEntity<Void> logout() {
        authService.removeToken();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
