package com.example.rocketmq.demo.transactionRocketMQ;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.*;

/**
 * @Description:
 * 分布式事务 生产者
 * @Author HeSuiJin
 * @Date 2021/4/19
 */
@Component
public class TransactionProducer {

//        DefaultMQProducer就是我们最普通的生产者
//        DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
//        TransactionMQProducer 继承了 DefaultMQProducer
    public void sendMessageInTransaction() throws MQClientException, InterruptedException {

        TransactionMQProducer transactionMQProducer = new TransactionMQProducer();
        //该生产者所在group
        transactionMQProducer.setProducerGroup("transaction_producer_group");
        ///如果是集群模式 以 ; 分开   "IP1:9876;IP2:9876;"
        transactionMQProducer.setNamesrvAddr("47.113.101.241:9876");
        //是否走Vip通道
        transactionMQProducer.setVipChannelEnabled(false);
        //设置发送失败重试次数
        transactionMQProducer.setRetryTimesWhenSendAsyncFailed(3);
        //消息同步发送失败重试次数
        transactionMQProducer.setRetryTimesWhenSendFailed(3);
        //消息异步发送失败重试次数
        transactionMQProducer.setRetryTimesWhenSendAsyncFailed(3);


        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("client-transaction-msg-check-thread");
                return thread;
            }
        });

        //创建事务实现类
        TransactionListener transactionListener = new TransactionListenerImpl();

        transactionMQProducer.setExecutorService(executorService);
        transactionMQProducer.setTransactionListener(transactionListener);
        transactionMQProducer.start();

        //设置Topic
        String topic = "transaction_test_topic";

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                Message msg =
                        new Message(topic, tags[i % tags.length], "KEY" + i,
                                ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = transactionMQProducer.sendMessageInTransaction(msg, null);
                System.out.printf("%s%n", sendResult);

                Thread.sleep(10);
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        //模拟事务一直不提交
        for (int i = 0; i < 100000; i++) {
            Thread.sleep(1000);
        }
        transactionMQProducer.shutdown();
    }
}
