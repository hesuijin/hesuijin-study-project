package com.example.jwt.base.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Author HeSuiJin
 * @Date 2021/3/12 12:30
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @NotBlank
    private String userName;
    private String password;
    private String fullName;
    private Boolean enabled;
}
