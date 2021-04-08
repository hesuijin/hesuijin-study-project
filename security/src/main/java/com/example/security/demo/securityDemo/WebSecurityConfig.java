package com.example.security.demo.securityDemo;

/**
 * @Description:
 *
 *  @EnableWebSecurity 注解使得 SpringMVC 集成了 Spring Security 的 web 安全支持
 * WebSecurityConfigurerAdapter，重写了其中的特定方法，用于自定义 Spring Security 配置。
 * 整个 Spring Security 的工作量，其实都是集中在该配置类，不仅仅是这个 guides，实际项目中也是如此。
 *
 * @Author HeSuiJin
 * @Date 2021/4/8
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * configure(HttpSecurity) 定义了哪些 URL 路径应该被拦截，
     * 如字面意思所描述：”/“, “/home” 允许所有人访问，”/login” 作为登录入口，也被允许访问，
     * 而剩下的 “/hello” 则需要登陆后才可以访问。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**
     * configureGlobal(AuthenticationManagerBuilder) 在内存中配置一个用户，
     * admin/admin 分别是用户名和密码，这个用户拥有 USER 角色。
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("admin").roles("USER");
    }
}
