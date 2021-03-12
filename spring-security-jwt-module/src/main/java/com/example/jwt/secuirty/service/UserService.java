package com.example.jwt.secuirty.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.jwt.base.PageModel;
import com.example.jwt.base.request.UserRegisterRequest;
import com.example.jwt.base.request.UserUpdateRequest;
import com.example.jwt.base.vo.UserNameVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Author HeSuiJin
 * @Date 2021/3/10 17:27
 * @Description:
 */
public interface UserService {

    void save(UserRegisterRequest userRegisterRequest);

    IPage<UserNameVO> getAll(PageModel pageModel);

    void update(UserUpdateRequest userUpdateRequest);

    void delete(String username);
}
