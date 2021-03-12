package com.example.security.mvc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.security.base.PageModel;
import com.example.security.base.request.UserRegisterRequest;
import com.example.security.base.request.UserUpdateRequest;
import com.example.security.base.response.UserNameResponse;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 17:27
 * @Description:
 */
public interface UserService {

    void save(UserRegisterRequest userRegisterRequest);

    IPage<UserNameResponse> getAll(PageModel pageModel);

    void update(UserUpdateRequest userUpdateRequest);

    void delete(String username);
}
