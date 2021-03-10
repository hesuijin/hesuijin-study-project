package com.example.jwt.base.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 12:04
 * @Description:
 */
@Data
@Builder
public class UserNameInfo {
    private String userName;
    private String fullName;
}
