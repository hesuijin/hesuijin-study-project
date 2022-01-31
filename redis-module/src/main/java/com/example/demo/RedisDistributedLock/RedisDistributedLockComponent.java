package com.example.demo.RedisDistributedLock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * Redis 分布式锁配置
 * @Author HeSuiJin
 * @Date 2021/4/12
 */
@Component
@Slf4j
public class RedisDistributedLockComponent {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 分布式锁
     */
//    public class DistributedLock {

        //锁超时时间 4秒
        private static final long DEFAULT_LOCK_TIME_OUT = 4000L;
        //请求超时时间5秒
        private static final long DEFAULT_ACQUIRE_TIME_OUT = 5000L;

        /**
         * 获取锁
         * @param key
         * @param acquireTimeout  不断重试添加锁 直到超时 或者成功
         * @param lockTimeOut     如果添加锁成功 该锁的有效时间
         * @return
         */
        public boolean getLock(String key, long acquireTimeout, long lockTimeOut) {
            //初始化锁为 false
            boolean lock = false;
            key = "lock:" + key;
            long now = System.currentTimeMillis();
            acquireTimeout = now + acquireTimeout;
            //如果入参acquireTimeout 小于等于 0  则使用默认值
            if (0L>= acquireTimeout ) {
                acquireTimeout = now + DEFAULT_ACQUIRE_TIME_OUT;
            }
            //如果入参lockTimeOut 小于等于 0  则使用默认值
            if (0 >= lockTimeOut) {
                lockTimeOut = DEFAULT_LOCK_TIME_OUT;
            }
            //只要是字符串就行了content
            byte[] content ="content".getBytes();
            RedisConnection connection = null;
            try {
                //获取redis的连接
                connection = stringRedisTemplate.getConnectionFactory().getConnection();

                //在acquireTimeout（请求锁超时时间内） 一直尝试添加锁 200L（0.2秒） 一次
                //超过时间则添加锁失败
                do {
                    //原子性 SET if Not Exists
                    //如果不存在 才能进行加锁
                    if (connection.setNX(key.getBytes(), content)) {
                        //设置该key对应的锁超时时间
                        stringRedisTemplate.expire(key, lockTimeOut, TimeUnit.MILLISECONDS);
                        //设置锁为true
                        lock = true;
                        break;
                    }
                    Thread.sleep(200L);
                    log.info("key : {} 等待锁", key);
                } while (System.currentTimeMillis() <= acquireTimeout);
            } catch (Exception e) {
                log.error("获取锁异常", e);
                return false;
            } finally {
                if (null != connection) {
                    connection.close();
                }
            }
            return lock;
        }

        /**
         * 释放锁
         * @param key
         * @return
         */
        public boolean releaseLock(String key) {
            boolean releaseLock = false;
            key = "lock:" + key;
            try {
                stringRedisTemplate.delete(key);
                 releaseLock = true;
            } catch (Exception e) {
                log.error("释放锁异常", e);
                return false;
            }
            return releaseLock;
        }
}
