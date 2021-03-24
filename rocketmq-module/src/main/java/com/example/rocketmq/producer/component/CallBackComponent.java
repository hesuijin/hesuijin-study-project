package com.example.rocketmq.producer.component;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @Author HeSuiJin
 * @Date 2021/3/18 23:08
 * @Description:
 */
@Component
public class CallBackComponent {
    public void execute(final CallBackActionComponent action) {

        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager
                    .registerSynchronization(new TransactionSynchronization() {
                        @Override
                        public void afterCommit() {
                            // 事务提交后执行回调
                            action.callback();
                        }
                    });
        } else {
            // 事务提交后执行回调
            action.callback();
        }

    }
}
