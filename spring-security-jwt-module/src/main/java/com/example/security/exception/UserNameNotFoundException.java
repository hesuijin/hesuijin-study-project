package com.example.security.exception;

import java.util.Map;

/**
 * @Author HeSuiJin
 * @Date 2021/3/12 11:53
 * @Description:
 */
public class UserNameNotFoundException extends BaseException{
    public UserNameNotFoundException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_NOT_FOUND, data);
    }
}
