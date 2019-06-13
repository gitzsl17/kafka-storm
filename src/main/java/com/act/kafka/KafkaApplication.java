package com.act.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("tutorialspoint", message);
    }

    @Override
    public void run(String... args) throws Exception {
        sendMessage("Hi Welcome to Spring For Apache Kafka @@@@@@@@@");
    }

    @KafkaListener(topics = "app_log", groupId = "test-consumer-group")
    public void listen(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }
}

