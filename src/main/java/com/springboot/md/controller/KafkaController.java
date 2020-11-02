package com.springboot.md.controller;

import com.springboot.md.config.AbstracController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-02 21:56
 * @Description: kafka
 */
@RestController
@Slf4j
public class KafkaController extends AbstracController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    // 发送消息
    @GetMapping("/kafka/normal/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
    }



}
