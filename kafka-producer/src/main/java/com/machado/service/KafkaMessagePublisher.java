package com.machado.service;

import com.machado.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;


@Service
@Slf4j
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;


    public void sendMessageToTopic(SampleDto sampleDto, String topic) throws Exception{
        CompletableFuture<SendResult<String, Object>> future = template.send(topic,sampleDto);
        //blocking
        //result.get();
        future.whenComplete( (result, ex) -> {
            if(Objects.nonNull(ex)) {
                log.info("Error while sending a message");
            }
            else log.info("Message request completed successfully offset {}",
                    result.getRecordMetadata().offset());
        });
    }
}
