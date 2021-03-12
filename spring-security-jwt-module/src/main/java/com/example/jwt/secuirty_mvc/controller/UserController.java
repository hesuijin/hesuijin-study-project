package com.example.jwt.secuirty_mvc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.jwt.base.PageModel;
import com.example.jwt.base.request.UserRegisterRequest;
import com.example.jwt.base.request.UserUpdateRequest;
import com.example.jwt.base.vo.UserNameVO;
import com.example.jwt.secuirty_mvc.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_MANAGER','ROLE_ADMIN')")
    @ApiOperation("获取所有用户的信息（分页）")
    public ResponseEntity<IPage<UserNameVO>> getAllUser(PageModel pageModel) {
        IPage<UserNameVO> allUser = userService.getAll(pageModel);
        return ResponseEntity.ok().body(allUser);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation("更新用户")
    public ResponseEntity<Void> update(@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        userService.update(userUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation("根据用户名删除用户")
    public ResponseEntity<Void> deleteUserByUserName(@RequestParam("username") String username) {
        userService.delete(username);
        return ResponseEntity.ok().build();
    }
}
