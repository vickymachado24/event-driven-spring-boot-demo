package com.machado.controller;

import com.machado.dto.SampleDto;
import com.machado.producer.KafkaProducerAvro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private KafkaProducerAvro producer;

    @PostMapping("/events")
    public String sendMessage(@RequestBody SampleDto employee) {
        producer.send(employee);
        return "Message has been successfully published !";
    }
}