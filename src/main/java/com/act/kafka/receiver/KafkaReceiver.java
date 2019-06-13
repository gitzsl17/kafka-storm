package com.act.kafka.receiver;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaReceiver {

    @KafkaListener(topics = {"tutorialspoint"})
    public void listen(ConsumerRecord<?, ?> record) {

        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            String topic = record.topic();
//            System.out.println("topic:" + topic);
//            System.out.println("receive-消息接收成功:" + "record =" + record);
            System.out.println("Received Messasge in group - group-id: " + message);
        }
    }
}
