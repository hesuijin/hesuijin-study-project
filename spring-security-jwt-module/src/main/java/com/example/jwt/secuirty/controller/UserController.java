package com.example.jwt.secuirty.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.jwt.base.request.UserRegisterRequest;
import com.example.jwt.secuirty.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 17:26
 * @Description:
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/users")
@Api(tags = "用户")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ApiOperation("用户注册")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        log.info("用户注册 请求入参 /users/sign-up： {}", userRegisterRequest);
        userService.save(userRegisterRequest);
        return ResponseEntity.ok().build();
    }

}
