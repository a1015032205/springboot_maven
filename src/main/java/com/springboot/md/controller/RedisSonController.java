package com.springboot.md.controller;

import com.springboot.md.aop.RedisLock;
import com.springboot.md.utils.RedisLockUtil;
import com.springboot.md.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-06 20:46
 * @Description: redis 分布式锁
 */

@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisSonController implements InitializingBean {

    private static String name;
    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Override
    public void afterPropertiesSet() throws Exception {
        name = redisUtil.get("name");
    }


    @RedisLock(key = "redisKey")
    @Scheduled(cron = "*/1 * * * * ?")
    public void task01() throws InterruptedException {
        Thread.sleep(5000L);
        String name = Thread.currentThread().getName();
        log.info(name + "===task01" + RedisSonController.name);
    }

    @RedisLock(key = "redisKey")
    @Scheduled(cron = "*/1 * * * * ?")
    public void task02() throws InterruptedException {
        Thread.sleep(5000L);
        String name = Thread.currentThread().getName();
        log.info(name + "==-task02" + name);
    }

    @RedisLock(key = "redisKey")
    @Scheduled(cron = "*/1 * * * * ?")
    public void task03() throws InterruptedException {
        Thread.sleep(5000L);
        String name = Thread.currentThread().getName();
        log.info(name + "===task03" + name);
    }

    @RequestMapping("/test")
    public void redissonTest() {
        String key = "redisson_key";
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                try {
                    System.err.println("=============线程开启============" + Thread.currentThread().getName());
                    RedisLockUtil.lock(key, 10L); //直接加锁，获取不到锁则一直等待获取锁
                    Thread.sleep(100); //获得锁之后可以进行相应的处理
                    System.err.println("======获得锁后进行相应的操作======" + Thread.
                            currentThread().getName());
                    RedisLockUtil.unlock(key); //解锁
                    System.err.println("=============================" +
                            Thread.currentThread().getName());

                    boolean isGetLock = RedisLockUtil.tryLock(key, TimeUnit.SECONDS, 5L, 10L); // 尝试获取锁，等待5秒，自己获得锁后一直不解锁则10秒后自动解锁
                    if (isGetLock) {
                        System.out.println("线程:" + Thread.currentThread().getName() + ",获取到了锁");
                        Thread.sleep(100); // 获得锁之后可以进行相应的处理
                        System.err.println("======获得锁后进行相应的操作======" + Thread.currentThread().getName());
                        //distributedLocker.unlock(key);
                        System.err.println("=============================" + Thread.currentThread().getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }
}
