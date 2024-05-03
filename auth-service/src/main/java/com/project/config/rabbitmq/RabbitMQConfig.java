package com.project.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String EXCHANGE_AUTH = "auth-exchange";
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_AUTH);
    }



    /**
     * Auth -> Manager; createMAnager methoduna mesaj gönderir.
     */
    private final String QUEUE_AUTH_CREATE_MANAGER = "auth-create-manager-queue";
    private final String BINDING_KEY_AUTH_CREATE_MANAGER = "auth-create-manager-binding-key";

    @Bean
    Queue queueAuthCreateManager(){
        return new Queue(QUEUE_AUTH_CREATE_MANAGER);
    }

    @Bean
    Binding bindingAuth(final DirectExchange directExchange, final Queue queueAuthCreateManager){
        return BindingBuilder.bind(queueAuthCreateManager).to(directExchange).with(BINDING_KEY_AUTH_CREATE_MANAGER);
    }


}
