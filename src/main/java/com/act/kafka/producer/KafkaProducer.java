package com.act.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

@Component
@EnableScheduling
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(cron = "0/10 * * * * ? ")
    public void send() {
        String message = UUID.randomUUID().toString();
        ListenableFuture future = kafkaTemplate.send("tutorialspoint", "邹圣力的kafka-" + message);
        future.addCallback(o -> System.out.println("send-消息发送成功：" + message), throwable ->
                System.out.println("消息发送失败: " + message));
    }

}
