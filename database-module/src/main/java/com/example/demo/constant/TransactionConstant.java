package com.example.demo.constant;

/**
 * @Description:事务常量
 * @Author HeSuiJin
 * @Date 2021/7/5
 */
public class TransactionConstant {

    public interface DataSourceTransactionManager {
        String MYSQL = "mysqlTransactionManager";
        String ORACLE = "oracleTransactionManager";

    }
}
