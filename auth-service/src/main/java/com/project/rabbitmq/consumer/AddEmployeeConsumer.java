package com.project.rabbitmq.consumer;

import com.project.dto.request.AddEmployeeRequestDto;
import com.project.rabbitmq.model.AddEmployeeModel;
import com.project.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddEmployeeConsumer {
    private final AuthService authService;

    @RabbitListener(queues = "add-employee-queue")
    public void createUserListener(AddEmployeeModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        authService.addEmployee(AddEmployeeRequestDto.builder()
                .email(model.getEmail())
                .name(model.getName())
                .surname(model.getSurname())
                .build());
    }

}
