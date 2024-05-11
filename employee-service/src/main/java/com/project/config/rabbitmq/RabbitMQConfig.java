package com.project.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    private final String EXCHANGE_EMPLOYEE = "employee-exchange";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_EMPLOYEE);
    }

    /**
     * Auth -> Employee; saveEmployee methodu ile iletisime gecen kuyruk
     */
    private final String QUEUE_AUTH_CREATE_EMPLOYEE = "auth-create-employee-queue";
    //dinleyecek kuyruk;
    private final String QUEUE_REQUIREMENTS_FIND_TOKEN_FOR_EMPLOYEE = "queue-requirements-find-token-for-employee";

    @Bean
    Queue queueRequirementsFindTokenForEmployee(){
        return new Queue(QUEUE_REQUIREMENTS_FIND_TOKEN_FOR_EMPLOYEE);
    }

    //mustafa auth;


    @Bean
    Queue queueAuthCreateEmployee(){
        return new Queue(QUEUE_AUTH_CREATE_EMPLOYEE);
    }



}
