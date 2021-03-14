package com.example.demo.filter.myFilter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author HeSuiJin
 * @Date 2021/3/14 21:43
 * @Description:
 */
@WebFilter(filterName = "Two_MyFilterWithAnnotation",value = "/api/annotation/*")
@Slf4j
public class MyFilterWithAnnotationTwo implements Filter{

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("初始化 注解生成过滤器2");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对请求进行预处理
        log.info("注解生成过滤器2 开始对请求进行预处理：");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUri = request.getRequestURI();
        System.out.println("注解生成过滤器2 请求的接口为：" + requestUri);
        long startTime = System.currentTimeMillis();
        //通过 doFilter 方法实现过滤功能
        filterChain.doFilter(servletRequest, servletResponse);
        // 上面的 doFilter 方法执行结束后用户的请求已经返回
        long endTime = System.currentTimeMillis();
        System.out.println("注解生成过滤器2 该用户的请求已经处理完毕，请求花费的时间为：" + (endTime - startTime));
    }

    @Override
    public void destroy() {
        log.info("销毁 注解生成过滤器2");
    }


}
