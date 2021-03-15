package com.example.demo.interceptor.myInterceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author HeSuiJin
 * @Date 2021/3/15 17:53
 * @Description:
 */
@Slf4j
public class OldLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        System.out.println("\n-------- OldLoginInterceptor.preHandle --- ");
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("Sorry! This URL is no longer used, Redirect to /admin/login");

        try {
            response.sendRedirect(request.getContextPath() + "/admin/login");
        } catch (IOException e) {
            log.info("重定向异常"+e.getMessage(),e);
        }
        //注意这里已经return false了
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler, ModelAndView modelAndView) {

        // This code will never be run.
        System.out.println("\n-------- OldLoginInterceptor.postHandle --- ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) {

        // This code will never be run.
        System.out.println("\n-------- QueryStringInterceptor.afterCompletion --- ");
    }

}
