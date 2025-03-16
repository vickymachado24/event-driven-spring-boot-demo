package com.machado.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic newTopic(){
        return new NewTopic("demo-topic-via-config", 4, (short) 1);
    }

    /***
     * Instead of having a properties file, config file does the same job
     * You need a producer? You'll need a producer factory bean with the same props
     * loaded as a map and then feed it to a DefaultKafkaProducerFactory constructor
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    /***
     * Without the above config, when we create a KafkaTemplate object to send messages,
     * it used the default configs, we need to create out own template with above config
     * Creating a kafka template bean will help us use the said bean factory and use it
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

}
