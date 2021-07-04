package com.example.demo.modules.oracleModule.controller;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/4
 */

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.Result;
import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;
import com.example.demo.modules.oracleModule.service.TransactionRollbackForService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactionRollbackFor")
@Slf4j
public class TransactionRollbackForController {

    @Autowired
    private TransactionRollbackForService transactionRollbackForService;


    @PostMapping("update1")
    public void updateTest1(MemberUpdateReq memberUpdateReq) {
        transactionRollbackForService.updateMemberByReq1(memberUpdateReq);

    }

    @PostMapping("update2")
    public void updateTest2(MemberUpdateReq memberUpdateReq) {
        transactionRollbackForService.updateMemberByReq2(memberUpdateReq);

    }

    @PostMapping("update3")
    public void updateTest3(MemberUpdateReq memberUpdateReq) {
        transactionRollbackForService.updateMemberByReq3(memberUpdateReq);
    }

    @PostMapping("update4")
    public void updateTest4(MemberUpdateReq memberUpdateReq) {
        transactionRollbackForService.updateMemberByReq4(memberUpdateReq);
    }

    @PostMapping("update5")
    public void updateTest5(MemberUpdateReq memberUpdateReq) {
        try {
            transactionRollbackForService.updateMemberByReq5(memberUpdateReq);
        } catch (RuntimeException e) {
            log.info("我报异常了:{}", e.getMessage(), e);
        }
    }

    @PostMapping("updateReturn1")
    public void updateReturnTest1(MemberUpdateReq memberUpdateReq) {
        String returnString = transactionRollbackForService.updateMemberByReqReturn1(memberUpdateReq);
        System.out.println(returnString);
    }

    @PostMapping("updateReturn2")
    public void updateReturnTest2(MemberUpdateReq memberUpdateReq) {
        String returnString = transactionRollbackForService.updateMemberByReqReturn2(memberUpdateReq);
        System.out.println(returnString);
    }

    @PostMapping("updateReturn3")
    public void updateReturnTestOver(MemberUpdateReq memberUpdateReq) {
        String returnString = new String();
        returnString = transactionRollbackForService.updateReturnTestOver(memberUpdateReq, returnString);
        System.out.println(returnString);
    }

    // try 两次
    @PostMapping("updateReturn4")
    @ResponseBody
    public String updateReturnTestOver2(MemberUpdateReq memberUpdateReq) {
        String returnString = "原本";
        Result result = new Result();
        try {
            returnString =  transactionRollbackForService.updateReturnTestOver2(memberUpdateReq, returnString);
            //上面报错
            //则 System.out.println(returnString); 不会走 而是直接进入 catch逻辑
            System.out.println(returnString);
        } catch (RuntimeException e) {
            log.info("外层 我报异常了:{}", e.getMessage(), e);
            //

            log.info("打印:{}"+returnString);
            return returnString;
        }
        return    returnString;
    }

    // try 两次
    @PostMapping("updateReturn5")
    @ResponseBody
    public Result updateReturnTestOver3(MemberUpdateReq memberUpdateReq) {
//        String returnString = "原本";
        Result result = new Result();
        result.setMsg("原本");
        try {
            result =  transactionRollbackForService.updateReturnTestOver3(memberUpdateReq, result);
            //上面报错
            //则 System.out.println(returnString); 不会走 而是直接进入 catch逻辑
            System.out.println(result.toString());
        } catch (RuntimeException e) {
            log.info("外层 我报异常了:{}", e.getMessage(), e);
            //

            log.info("打印:{}"+ JSONObject.toJSONString(result));
            return result;
        }
        log.info("打印:{}"+ JSONObject.toJSONString(result));
        return    result;
    }

    // try 两次
    @PostMapping("updateReturnJunit")
    @ResponseBody
    public Result updateReturnTestJunit(MemberUpdateReq memberUpdateReq) {
        Result result = new Result();
        result.setMsg("原本");
        try {
            result =  transactionRollbackForService.updateReturnTestJunit(memberUpdateReq, result);
            //上面报错
            //则 System.out.println(returnString); 不会走 而是直接进入 catch逻辑
//            System.out.println(result.toString());
        } catch (RuntimeException e) {
            log.info("外层 我报异常了:{}", e.getMessage(), e);
            log.info("打印:{}"+ JSONObject.toJSONString(result));
            return result;
        }
        log.info("打印:{}"+ JSONObject.toJSONString(result));
        return    result;
    }
}