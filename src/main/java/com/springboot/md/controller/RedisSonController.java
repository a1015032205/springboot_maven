package com.springboot.md.controller;

import com.springboot.md.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-06 20:46
 * @Description: redis 分布式锁
 */

@Component
@Slf4j
public class RedisSonController implements InitializingBean {

    private static String name;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void afterPropertiesSet() throws Exception {
        name = redisUtil.get("name");
    }


    @Scheduled(cron = "*/1 * * * * ?")
    public void task01() {
        log.info("task01" + name);
    }

    @Scheduled(cron = "*/1 * * * * ?")
    public void task02() {
        log.info("task02" + name);
    }

    @Scheduled(cron = "*/1 * * * * ?")
    public void task03() {
        log.info("task03" + name);
    }
}
