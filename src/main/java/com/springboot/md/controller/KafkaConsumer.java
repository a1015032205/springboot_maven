package com.springboot.md.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: 秒度
 * @Email: fangxin.md@Gmail.com
 * @Date: 2020-11-02 21:56
 * @Description: kafka
 */

@Component
@Slf4j
public class KafkaConsumer {
    @KafkaListener(topics = {"topic1"})
    public void consumer(ConsumerRecord<?, ?> consumerRecord) {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            System.err.println("消费消息:" + message);
        }
    }
}