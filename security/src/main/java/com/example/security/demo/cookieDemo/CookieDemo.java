package com.example.security.demo.cookieDemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author hesuijin
 * @Description
 * @Param
 * @Return
 * @Date 2021/3/2
 */
@RestController
@RequestMapping("/cookieDemo")
public class CookieDemo {

    /**
     * 使⽤Spring框架提供的 @CookieValue 注解获取特定的 cookie的值
     * @param username
     * @return
     */
    @GetMapping("/cookie-value")
    public String readCookie(@CookieValue(value = "username", defaultValue =
            "hsj") String username) {
        return "Hey! My username is " + username;
    }

    /**
     * 设置cookie返回给客户端
     * @param response
     * @return
     */
    @GetMapping("/change-username")
    public String setCookie(HttpServletResponse response) throws InterruptedException {
        // 创建⼀个 cookie
        Cookie cookie = new Cookie("username", "HeSuiJin");
//        Cookie cookie = new Cookie("username", null);
        //设置 cookie过期时间
        // expires in 7 days
        cookie.setMaxAge(7 * 24 * 60 * 60);
        //添加到 response 中
        response.addCookie(cookie);
        return "Username is changed!";
    }

    /**
     * 读取所有的 Cookie 值
     * @param request
     * @return
     */
    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies).map(c -> c.getName() + "=" +
                    c.getValue()).collect(Collectors.joining(", "));
        }
        return "No cookies";
    }
}
