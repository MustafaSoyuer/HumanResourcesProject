package com.project.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String EXCHANGE_COMMENT = "comment-exchange";
    private final String QUEUE_COMMENT = "comment-queue";
    private final String BINDING_KEY_COMMENT = "comment-binding-key";

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_COMMENT);
    }

    @Bean
    Queue queueAuth(){
        return new Queue(QUEUE_COMMENT);
    }

    @Bean
    Binding bindingAuth(final DirectExchange directExchange, final Queue queueAuth){
        return BindingBuilder.bind(queueAuth).to(directExchange).with(BINDING_KEY_COMMENT);
    }


}
