package com.machado.producer;


import com.machado.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaProducerAvro {

    @Autowired
    private KafkaTemplate<String, SampleDto> template;


    public void send(SampleDto employee){
        CompletableFuture<SendResult<String, SampleDto>> future = template.send("demo-topic", UUID.randomUUID().toString(),employee);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message sent successfully {}", result.getRecordMetadata().offset());
            } else {
                System.out.println("Unable to send message=[" +
                        employee + "] due to : " + ex.getMessage());
            }
        });
    }
}
