package com.example.demo.interceptor.myInterceptor;

import com.example.demo.interceptor.myAnnotation.InterceptorAnnotationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author HeSuiJin
 * @Date 2021/3/15 17:47
 * @Description:
 */
@Slf4j
public class AnnotationLogInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        System.out.println("\n-------- AnnotationLogInterceptor.preHandle --- ");

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            InterceptorAnnotationLog interceptorAnnotationLog = method.getMethodAnnotation(InterceptorAnnotationLog.class);
            // 判断  存在interceptorIsStringResult注解  同时 注解的booleanValue值为true时 存放信息到MongoDB
            if ((interceptorAnnotationLog != null && interceptorAnnotationLog.booleanValue())) {
                try {
                    log.info("//待完成 从request获取信息  并存入 MongoDB");
                } catch (Exception e) {
                    log.info("获取请求信息 并存入MongoDB异常 "+e.getMessage(),e);
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler, ModelAndView modelAndView) {
        System.out.println("\n-------- AnnotationLogInterceptor.postHandle --- ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) {
        System.out.println("\n-------- AnnotationLogInterceptor.afterCompletion --- ");
    }


}
