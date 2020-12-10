package com.springboot.md.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-12-11 0:35
 * @Description:
 */

@Configuration
public class RedissonManager {

    @Value("${redisson.address}")
    private String addressUrl;

    @Bean
    public RedissonClient getRedisson() throws Exception {
        RedissonClient redisson;
        Config config = new Config();
        config.useSingleServer().setAddress(addressUrl);
        redisson = Redisson.create(config);
        System.out.println(redisson.getConfig().toJSON());
        return redisson;
    }


}