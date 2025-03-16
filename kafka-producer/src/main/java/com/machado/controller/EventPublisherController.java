package com.machado.controller;


import com.machado.dto.SampleDto;
import com.machado.service.KafkaMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event/producer/")
@Slf4j
public class EventPublisherController {


    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;


    @PostMapping("/save/{topic}")
    public ResponseEntity<?> sendMessage(@PathVariable("topic") String topic,
                                         @RequestBody SampleDto sampleDto){
        try {
            kafkaMessagePublisher.sendMessageToTopic(sampleDto, topic);
            return ResponseEntity.ok("Message sent successfully");
        }catch( Exception ex){
            log.error("Error in {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
