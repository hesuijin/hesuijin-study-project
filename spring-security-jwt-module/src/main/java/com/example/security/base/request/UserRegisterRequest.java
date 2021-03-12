package com.example.security.base.request;

import com.example.security.base.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 17:29
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    @NotBlank
    private String fullName;

    public User getUser() {
        return User.builder()
                .fullName(this.getFullName())
                .userName(this.getUserName())
                .enabled(true)
                .build();
    }
}
