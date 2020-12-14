package com.springboot.md.aop;

import com.springboot.md.utils.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-12 23:48
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class RedisLockAspect {

    @Pointcut("@annotation(com.springboot.md.aop.RedisLock)")
    public void redisLock() {

    }

    @Around("redisLock()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // log.info("第一个执行around");
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        RedisLock myAnnotation = method.getAnnotation(RedisLock.class);
        String key = myAnnotation.key();
        boolean b = RedisLockUtil.tryLock(key, 1L, 5L);
        String name = Thread.currentThread().getName();
        if (b) {
            log.info("{}拿到锁了，准备执行", name);
            return joinPoint.proceed();
        } else {
            log.info("{}没有拿到", name);
            return null;
        }

//        if (heldByCurrentThread) {
//            log.info("{}拿到锁了，准备执行", name);
//            return joinPoint.proceed();
//        }

        // log.info("{}没有拿到", name);


    }

    @Before("redisLock()")
    public void before(JoinPoint joinPoint) throws InterruptedException {
        //  log.info("第二个执行before之后 执行业务代码");
        //等待结束后才执行业务代码
        //   TimeUnit.SECONDS.sleep(3L);

    }

    @AfterReturning("redisLock()")
    public void afterReturn(JoinPoint joinPoint) {

        //   log.info("第三执行afterReturn");
    }


    @After("redisLock()")
    public void after(JoinPoint joinPoint) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        RedisLock myAnnotation = method.getAnnotation(RedisLock.class);
        String key = myAnnotation.key();
        RedisLockUtil.unlock(key);
        String name = Thread.currentThread().getName();
        log.info(name + "释放了锁");
        //     log.info("第四after");
    }

    @AfterThrowing("redisLock()")
    public void afterThrowing() {
        //   log.info("afterThrowing");
    }


}
