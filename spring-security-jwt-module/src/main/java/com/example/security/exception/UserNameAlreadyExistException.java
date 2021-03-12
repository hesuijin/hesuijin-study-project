package com.example.security.exception;

import java.util.Map;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 17:35
 * @Description:
 */
public class UserNameAlreadyExistException extends BaseException {

    public UserNameAlreadyExistException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_ALREADY_EXIST, data);
    }
}
