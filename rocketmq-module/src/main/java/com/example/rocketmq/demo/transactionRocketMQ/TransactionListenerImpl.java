package com.example.rocketmq.demo.transactionRocketMQ;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * 实现本地事务
 *
 * refer from http://rocketmq.apache.org/docs/transaction-example/
 * @Author HeSuiJin
 * @Date 2021/4/19
 */
@Slf4j
public class TransactionListenerImpl implements TransactionListener {

    private AtomicInteger transactionIndex = new AtomicInteger(0);
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    /**
     * when send transactional prepare(half) message succeed,
     * this method will be invoked to execute local transaction.
     * 当发送半消息成功后
     * 可以设置该消息在broker的状态为
     *      COMMIT_MESSAGE（Broker端直接消费）
     *      ROLLBACK_MESSAGE（Broker端直接回滚）
     *      UNKNOW（1分钟内才能 进入checkLocalTransaction的回查逻辑）
     *      null  (立刻进入反查逻辑)
     *
     * @param msg
     * @param otherParam
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object otherParam) {

        log.info("executeLocalTransaction:{} ", msg.getTransactionId());

        //模拟生成订单号  0 到 9
        //使用orderNo进行各种业务处理  然后返回一个 status
        //这里暂时设置根据orderNo执行各种业务逻辑后  返回的status为如下
        Integer orderNo = transactionIndex.getAndIncrement();
        Integer status = orderNo % 3;

        //在这里模拟本地事务的状态
        //有四种状态  UNKNOW  COMMIT_MESSAGE   ROLLBACK_MESSAGE  以及null
        //如果是 UNKNOW 会在00秒后进入checkLocalTransaction 逻辑
        //如果是 null   会立即进入checkLocalTransaction 逻辑

        if (null != status) { switch (status) {
                case 0:
//                  订单号  0  3  6 9  成功
                    localTrans.put(msg.getTransactionId(), orderNo);
                    return LocalTransactionState.COMMIT_MESSAGE;
                case 1:
//                   订单号  1  4  7  失败
                    localTrans.put(msg.getTransactionId(), orderNo);
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                case 2:
                    //该msg.getTransactionId() 需要在回调时使用
//                    订单号  2   5   8  进入回调
                    localTrans.put(msg.getTransactionId(), orderNo);
                    return LocalTransactionState.UNKNOW;
                default:
                    //该msg.getTransactionId() 需要在回调时使用
                    localTrans.put(msg.getTransactionId(), orderNo);
                    return null;
            }
        }
        return null;
    }

    /**
     * When no response to prepare(half) message. broker will send check message to check the transaction status,
     * and this method will be invoked to get local transaction status.
     *
     *  在进行发送消息后 到到Broker变成半消息状态
     *  执行完executeLocalTransaction  其状态是  UNKNOW 或者 null  则需要进入回查处理
     *
     * @param msg
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {

        String transactionId = msg.getTransactionId();
        Integer orderNo =  localTrans.get(transactionId);
        log.info("执行的逻辑 checkLocalTransaction：{}  orderNo:{} ",transactionId,orderNo);

        //根据orderNo进行逻辑回查处理
        //进行回查的结果 只能是 COMMIT_MESSAGE(成功) 或者  ROLLBACK_MESSAGE（失败）
        //这里暂时设置根据orderNo执行各种业务逻辑后  返回为如下
        if (orderNo % 2 == 0) {
            log.info("执行的逻辑 checkLocalTransaction: {}  orderNo:{},COMMIT_MESSAGE", msg.getTransactionId(),orderNo);
            return LocalTransactionState.COMMIT_MESSAGE;

        }else
        {
            log.info("执行的逻辑 checkLocalTransaction: {}  orderNo:{},ROLLBACK_MESSAGE", msg.getTransactionId(),orderNo);
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }
}
