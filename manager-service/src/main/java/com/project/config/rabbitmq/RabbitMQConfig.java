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
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_MANAGER);
    }


    private final String QUEUE_MANAGER = "manager-queue";

    private final String BINDING_KEY_MANAGER = "manager-binding-key";

    @Bean
    Queue queueManager(){
        return new Queue(QUEUE_MANAGER);
    }

    @Bean
    Binding bindingManager(final DirectExchange directExchange, final Queue queueManager){
        return BindingBuilder.bind(queueManager).to(directExchange).with(BINDING_KEY_MANAGER);
    }


    /**
     * Auth -> Manager; createManager methoduna mesaj gönderir.
     */
    private final String QUEUE_AUTH_CREATE_MANAGER = "auth-create-manager-queue";
    @Bean
    Queue queueAuthCreateManager(){
        return new Queue(QUEUE_AUTH_CREATE_MANAGER);
    }



    /**
     * Manager -> Company; createCompany methodu ile iletişime geçen kuyruk.
     */

    private final String QUEUE_CREATE_COMPANY = "create-company-queue";
    private final String BINDING_KEY_CREATE_COMPANY = "create-company-binding-key";
    @Bean
    Queue queueCreateCompany(){
        return new Queue(QUEUE_CREATE_COMPANY);
    }

    @Bean
    Binding bindingCreateCompany(final DirectExchange directExchange, final Queue queueCreateCompany){
        return BindingBuilder.bind(queueCreateCompany).to(directExchange).with(BINDING_KEY_CREATE_COMPANY);
    }


}
