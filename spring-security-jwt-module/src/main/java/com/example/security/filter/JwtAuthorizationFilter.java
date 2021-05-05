package com.example.security.filter;

import com.example.security.common.constants.SecurityConstants;
import com.example.security.utils.JwtTokenUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author HeSuiJin
 * @Date 2021/3/12 15:58
 * @Description:
 * 过滤器处理所有HTTP请求，并检查是否存在带有正确令牌的Authorization标头。例如，如果令牌未过期或签名密钥正确。
 * 如果校验通过 可以存放用户关键信息到 SecurityContextHolder 中
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private  StringRedisTemplate stringRedisTemplate;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate) {
        super(authenticationManager);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    //没有对请求进行真正的过滤 只是用于把Token中的重要用户信息存储在SecurityContextHolder中
    //最后会在 SecurityConfiguration上真正的对权限信息的判断
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        //1:获取请求头中的Token
        //  如果请求头中没有Authorization信息则直接放行了
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }
        String tokenValue = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            //2:如果请求头中没有Authorization信息  则获取Token中用户Id
            String userId = JwtTokenUtils.getId(tokenValue);
            //3：获取redis中数据  根据  key 为用户Id  获取  value 为Token
            String previousToken = stringRedisTemplate.opsForValue().get(userId);
            //4:对比Token是否一致
            //   如果Token不一致则直接放行
            if (!token.equals(previousToken)) {
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                return;
            }
            //5:使用 用户名称  token  用户角色 组合成  UsernamePasswordAuthenticationToken类型数据
            authentication = JwtTokenUtils.getAuthentication(tokenValue);
        } catch (JwtException e) {
            logger.error("Invalid dto : " + e.getMessage());
        }
        //6：把 UsernamePasswordAuthenticationToken类型数据存放在  SecurityContextHolder 中 全局使用
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
