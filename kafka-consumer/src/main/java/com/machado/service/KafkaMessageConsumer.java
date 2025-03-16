package com.machado.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageConsumer {

    @KafkaListener(
            topics = "demo-topic-via-config",
            groupId = "demo-topic-config-consumer-group-1",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(Object message) {
        log.info("Message consumed - {}", message.toString());
    }

}
