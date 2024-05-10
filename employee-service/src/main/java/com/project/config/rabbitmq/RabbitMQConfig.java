package com.project.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String EXCHANGE_EMPLOYEE = "employee-exchange";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_EMPLOYEE);
    }

    /**
     * Auth -> Employee; saveEmployee methodu ile iletisime gecen kuyruk
     */
    private final String QUEUE_AUTH_CREATE_EMPLOYEE = "auth-create-employee-queue";

    @Bean
    Queue queueAuthCreateEmployee(){
        return new Queue(QUEUE_AUTH_CREATE_EMPLOYEE);
    }




}
