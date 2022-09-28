package com.dh.serieservice.config;



import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Iterator;

@Configuration
public class RabbitMQConsumerConfig {

    @Value("${queue.serie.name}")
    private String serieQueue;

    @Bean
    public org.springframework.amqp.core.Queue queue() {
        return new Queue(this.serieQueue, true);
    }


}
