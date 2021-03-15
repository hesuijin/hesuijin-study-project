package com.example.demo.interceptor.controller;

import com.example.demo.interceptor.myAnnotation.InterceptorAnnotationLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注意方法   添加@ResponseBody 可以返回Json字符串
 * @Author HeSuiJin
 * @Date 2021/3/15 17:40
 * @Description:
 */
@Controller
public class InterceptorController {

    //  "/"  或者 "/test" 都可以进入该方法
    @RequestMapping(value = { "/", "/test" })
    public String logTest() {
        System.out.println("\n-------- MainController.test --- ");
        System.out.println(" ** You are in Controller log ** ");
        return "test";
    }

    // This path is no longer used.
    // It will be redirected by OldLoginInterceptor
    @Deprecated
    @RequestMapping("/admin/oldLogin")
    public String adminOldLogin() {
        return "oldLogin";
    }

    @RequestMapping("/admin/login" )
    public String adminLogin() {
        System.out.println("\n-------- MainController.login --- ");
        System.out.println(" ** You are in  Controller /admin/login ** ");
        return "login";
    }
    @InterceptorAnnotationLog(booleanValue=true)
    @RequestMapping("/annotationLog" )
    public String returnString( ) {
        System.out.println("\n-------- MainController.annotationLog --- ");
        System.out.println(" ** You are in  Controller annotationLog ** ");
        return "annotationLog";
    }
}
