//package com.example.security.source_code;
//
////核心代码阅读
//import org.springframework.security.authentication.ProviderManager;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.MessageSourceAware;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderNotFoundException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.CredentialsContainer;
//
//import java.util.Collections;
//import java.util.List;
//
//public class ProviderManager implements AuthenticationManager, MessageSourceAware, InitializingBean {
//
//    // 维护一个 AuthenticationProvider 列表
//    private List<AuthenticationProvider> providers = Collections.emptyList();
//
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        Class<? extends Authentication> toTest = authentication.getClass();
//        AuthenticationException lastException = null;
//        Authentication result = null;
//
//        // 依次认证
//        for (AuthenticationProvider provider : getProviders()) {
//            if (!provider.supports(toTest)) {
//                continue;
//            }
//            try {
//                result = provider.authenticate(authentication);
//
//                if (result != null) {
//                    copyDetails(authentication, result);
//                    break;
//                }
//            } catch (AuthenticationException e) {
//                lastException = e;
//            }
//        }
//        // 如果有 Authentication 信息，则直接返回
//
//        //ProviderManager 中的 List，会依照次序去认证，认证成功则立即返回，若认证失败则返回 null，
//        //下一个 AuthenticationProvider 会继续尝试认证，
//        //如果所有认证器都无法认证成功，则 ProviderManager 会抛出一个 ProviderNotFoundException 异常。
//
//        if (result != null) {
//            if (eraseCredentialsAfterAuthentication && (result instanceof CredentialsContainer)) {
//                // 移除密码
//                ((CredentialsContainer) result).eraseCredentials();
//            }
//            // 发布登录成功事件
//            eventPublisher.publishAuthenticationSuccess(result);
//            return result;
//        }
//
//        // 执行到此，说明没有认证成功，包装异常信息
//        if (lastException == null) {
//            lastException = new ProviderNotFoundException(messages.getMessage(
//                    "ProviderManager.providerNotFound",
//                    new Object[]{toTest.getName()},
//                    "No AuthenticationProvider found for {0}"));
//        }
//        prepareException(lastException, authentication);
//        throw lastException;
//    }
//}