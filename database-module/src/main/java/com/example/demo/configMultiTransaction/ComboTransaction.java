package com.example.demo.configMultiTransaction;

import com.alibaba.druid.util.StringUtils;
import com.example.demo.constant.TransactionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Component
public class ComboTransaction {
 
    @Autowired
    private Db1TxBroker db1TxBroker;
 
    @Autowired
    private Db2TxBroker db2TxBroker;
 
    public <V> V inCombinedTx(Callable<V> callable, String[] transactions) {
        if (callable == null) {
            return null;
        }
 
        Callable<V> combined = Stream.of(transactions)
                .filter(ele -> !StringUtils.isEmpty(ele))
                .distinct()
                .reduce(callable, (r, tx) -> {
                    switch (tx) {
                        case TransactionConstant.DataSourceTransactionManager.ORACLE:
                            return () -> db1TxBroker.inTransaction(r);
                        case TransactionConstant.DataSourceTransactionManager.MYSQL:
                            return () -> db2TxBroker.inTransaction(r);
                        default:
                            return null;
                    }
                }, (r1, r2) -> r2);
 
        try {
            return combined.call();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}