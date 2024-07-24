package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "taskTopic", groupId = "demo-group")
    public void consumeTask(String message) {
        System.out.println("Consumed message: " + message);
        // Process the task
    }
}