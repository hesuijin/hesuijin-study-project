//package com.example.security.source_code;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
///**
// * @Author hesuijin
// * @Description
// * @Param
// * @Return
// * @Date 2021/3/34
// */
//public class AuthenticationExample {
//
//    private static AuthenticationManager am = new SampleAuthenticationManager();
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//
//        while (true) {
//            System.out.println("Please enter your username:");
//            String name = in.readLine();
//            System.out.println("Please enter your password:");
//            String password = in.readLine();
//            try {
//                //name 为 principal   password 为 credentials
//                Authentication request = new UsernamePasswordAuthenticationToken(name, password);
//                Authentication result = am.authenticate(request);
//                SecurityContextHolder.getContext().setAuthentication(result);
//                break;
//            } catch (AuthenticationException e) {
//                System.out.println("Authentication failed:" + e.getMessage());
//            }
//        }
//        System.out.println("Successfully authenticated. Security context contains:" +
//                SecurityContextHolder.getContext().getAuthentication());
//    }
//}