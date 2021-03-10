package com.example.jwt.component;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


/**
 * user通用逻辑组件
 * @Author HeSuiJin
 * @Date 2021/3/10 11:36
 * @Description:
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserComponent {

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean userCheck(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }
}
