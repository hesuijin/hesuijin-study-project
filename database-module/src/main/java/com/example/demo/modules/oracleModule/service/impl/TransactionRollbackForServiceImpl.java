package com.example.demo.modules.oracleModule.service.impl;

import com.example.demo.modules.oracleModule.base.request.MemberUpdateReq;
import com.example.demo.constant.Result;
import com.example.demo.modules.oracleModule.dao.MemberMapper;
import com.example.demo.modules.oracleModule.service.TransactionRollbackForService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author HeSuiJin
 * @Date 2021/7/4
 */
@Service
@Slf4j
public class TransactionRollbackForServiceImpl implements TransactionRollbackForService {

    @Autowired
    private MemberMapper memberMapper;

    //rollbackFor  Transactional的事务回滚标志  当Transactional检查到 对应的 Exception.class 异常时会回滚
    // Exception
    // 非运行期异常 IOException  SQLException （checked exceptions 可查异常）  必须显式捕获  否则编译期不会通过
    // 运行期异常   NullPointerException (unchecked exceptions 不可查异常)  运行中逻辑错误引起的  可以对有可能发生异常的位置进行捕获

//    1：没有事务
//    2：没有事务 并 报非运行期异常
//    3：添加事务 并 报非运行期异常
//    4：添加事务 并 报非运行期异常 然后在事务内捕获
//    5：添加事务 并 报非运行期异常 然后在事务外捕获

//    6：添加事务 并 报非运行期异常 然后在事务内捕获  并且再往外抛新的异常
//     7：throw 与 return 不能共存

//    事务回滚比较好的解决方案：
//    1：controller加 try catch  如果 service 报错  service的事务可以感知到  然后回滚  然后controller可以把异常打印出来
//            但这样子没有办法return 自定义的信息
//    2：Service 层加 try catch  但不throw  而是 return  可以自定义信息
//            但这样子没有办法 让事务感知后回滚
//
//    3：最终解决方案 try两次
//        Service 层加 try catch  而且throw   让service的事务可以感知到  同时想要的return 可以放在方法入参传递进来
//       controller加 try catch 把 Service 异常处理掉  让方法可以顺利执行完

//    try catch 中的catch中不运行return


    //原则 修改代码放在后面

    //注意需要指定事务
    @Override
    public void updateMemberByReq1(MemberUpdateReq memberUpdateReq) {
        memberMapper.updateMemberByReq(memberUpdateReq);

    }

    @Override
    //报错后数据还是正常插入
    public void updateMemberByReq2(MemberUpdateReq memberUpdateReq) {
        memberMapper.updateMemberByReq(memberUpdateReq);
        int a = 1/0;
    }

    //多数据源导致的无法回滚 因此需要指定数据源
    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    //报错后数据回滚
    @Override
    public void updateMemberByReq3(MemberUpdateReq memberUpdateReq) {
        memberMapper.updateMemberByReq(memberUpdateReq);
        int a = 1/0;
    }

    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    @Override
    //报错后  事务内捕获异常  事务没有感知 数据不回滚
    public void updateMemberByReq4(MemberUpdateReq memberUpdateReq) {
       try {
           memberMapper.updateMemberByReq(memberUpdateReq);
           int a = 1/0;
       }catch (RuntimeException e){
           log.info( "我报异常了:{}",e.getMessage(),e);
       }
    }

    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    @Override
    //报错后  事务外捕获异常 事务感知异常 数据回滚
    public void updateMemberByReq5(MemberUpdateReq memberUpdateReq) {
        memberMapper.updateMemberByReq(memberUpdateReq);
        int a = 1/0;
    }

    @Override
    //报错后  事务内捕获异常  然后再次抛出给到事务  事务感知 数据回滚
    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    public String updateMemberByReqReturn1(MemberUpdateReq memberUpdateReq) {
        try {
            memberMapper.updateMemberByReq(memberUpdateReq);
            int a = 1/0;
        }catch (RuntimeException e){
            log.info( "我报异常了:{}",e.getMessage(),e);
            throw  e;
        }
        return "success";
    }



    @Override
    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    //报错后  事务内捕获异常  事务没有感知 数据不回滚
    public String updateMemberByReqReturn2(MemberUpdateReq memberUpdateReq) {
        try {
            memberMapper.updateMemberByReq(memberUpdateReq);
            int a = 1/0;
        }catch (RuntimeException e){
            log.info( "我报异常了:{}",e.getMessage(),e);
            return "fail";
        }
        return "success";
    }

    @Override
    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    //报错后  事务内捕获异常  然后再次抛出给到事务  事务感知 数据回滚
    //并且 返回值通过入参传递 即是可以在数据回滚的同时 赋予返回值
    public String updateReturnTestOver(MemberUpdateReq memberUpdateReq, String returnString) {
        try {
            memberMapper.updateMemberByReq(memberUpdateReq);
            int a = 1/0;
        }catch (RuntimeException e){
            log.info( "我报异常了:{}",e.getMessage(),e);
            returnString = "fail";
            throw  e;
        }
        return "success";
    }

    @Override
    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    //报错后  事务内捕获异常  然后再次抛出给到事务  事务感知 数据回滚
    //并且 返回值通过入参传递 即是可以在数据回滚的同时 赋予返回值
    public String updateReturnTestOver2(MemberUpdateReq memberUpdateReq, String returnString) {
        try {
            memberMapper.updateMemberByReq(memberUpdateReq);
            int a = 1/0;
        }catch (RuntimeException e){
            log.info( "里层 我报异常了:{}",e.getMessage(),e);
            //这个值传递不出去 使用dot即可
            returnString = "我的 fail";
            throw  e;
        }
        return "success";
    }

    @Override
    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    public Result updateReturnTestOver3(MemberUpdateReq memberUpdateReq, Result result) {
        try {
            memberMapper.updateMemberByReq(memberUpdateReq);
            int a = 1/0;
        }catch (RuntimeException e){
            log.info( "里层 我报异常了:{}",e.getMessage(),e);
            result.setMsg("fail");
            throw  e;
        }
        result.setMsg("success");
        return  result;
    }

    @Override
    @Transactional(transactionManager ="oracleTransactionManager",rollbackFor = RuntimeException.class)
    public Result updateReturnTestJunit(MemberUpdateReq memberUpdateReq, Result result) {
        memberMapper.updateMemberByReq(memberUpdateReq);
        return null;
    }


    //以参数传递的方式 把参数传出去
    //既有 return  又有 回滚
//    @Override
//    @Transactional(rollbackFor = RuntimeException.class)
//    public void queryAlipayCnTradeOrder(String orderNo,ApiResult apiResult){
//        String trade_success = "TRADE_SUCCESS";
//        try {
//                apiResult.setStatus(20001);
//
//        }catch (AlipayApiException e){
//            logger.info("大陆支付宝查询交易订单, 调用异常, 订单：{}, e: {}",orderNo,e.getMessage());
//            apiResult.setStatus(20001);
//            apiResult.setMsg("请重试");
//        }
//    }


    // 单元测试导致的无法回滚
//    https://blog.csdn.net/Vincilovefang/article/details/113918071

}
