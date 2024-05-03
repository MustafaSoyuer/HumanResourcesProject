package com.project.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**
     * Manager -> Company; createCompany methodu ile iletişime geçen kuyruk.
     */
    private final String QUEUE_CREATE_COMPANY = "create-company-queue";
    @Bean
    Queue queueCreateCompany(){
        return new Queue(QUEUE_CREATE_COMPANY);
    }




}
