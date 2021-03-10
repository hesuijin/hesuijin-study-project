package com.example.jwt.exception;

import java.util.Map;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 18:01
 * @Description:
 */
public class RoleNotFoundException extends BaseException {
    public RoleNotFoundException(Map<String, Object> data) {
        super(ErrorCode.Role_NOT_FOUND, data);
    }
}
