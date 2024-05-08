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
     * Manager -> Employee; ManagerOrAdminUpdateEmployee methodu ile iletisime gecen kuyruk
     * Admin -> Employee; ManagerOrAdminUpdateEmployee methodu ile iletisime gecen kuyruk
     */
    private final String QUEUE_MANAGER_OR_ADMIN_UPDATE_EMPLOYEE = "manager-or-admin-update-employee-queue";
    @Bean
    Queue queueManagerOrAdminUpdateEmployee(){
        return new Queue(QUEUE_MANAGER_OR_ADMIN_UPDATE_EMPLOYEE);
    }

    /**
     * Employee -> Auth; addEmployee methodu ile iletisime gecen kuyruk
     */
    private final String QUEUE_ADD_EMPLOYEE = "add-employee-queue";
    private final String BINDING_KEY_ADD_EMPLOYEE = "add-employee-binding-key";

    @Bean
    Queue queueAddEmployee(){
        return new Queue(QUEUE_ADD_EMPLOYEE);
    }

    @Bean
    Binding bindingAddEmployee(final DirectExchange directExchange, final Queue queueAddEmployee){
        return BindingBuilder.bind(queueAddEmployee).to(directExchange).with(BINDING_KEY_ADD_EMPLOYEE);
    }


}
