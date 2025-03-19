package com.machado.consumer;


import com.machado.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerAvro {

    @KafkaListener(topics = "demo-topic")
    public void read(ConsumerRecord<String, SampleDto> consumerRecord) {
        String key = consumerRecord.key();
        SampleDto employee = consumerRecord.value();
        log.info("Avro message received successfully with key {} and employee {}", key, employee);

    }
}
