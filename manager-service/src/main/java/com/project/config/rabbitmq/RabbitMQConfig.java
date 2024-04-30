package com.project.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String EXCHANGE_MANAGER = "manager-exchange";
    private final String QUEUE_MANAGER = "manager-queue";
    private final String QUEUE_AUTH = "auth-queue";
    private final String BINDING_KEY_MANAGER = "manager-binding-key";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_MANAGER);
    }

    @Bean
    Queue queueManager(){
        return new Queue(QUEUE_MANAGER);
    }

    @Bean
    Queue queueAuth(){
        return new Queue(QUEUE_AUTH);
    }

    @Bean
    Binding bindingManager(final DirectExchange directExchange, final Queue queueManager){
        return BindingBuilder.bind(queueManager).to(directExchange).with(BINDING_KEY_MANAGER);
    }


}
