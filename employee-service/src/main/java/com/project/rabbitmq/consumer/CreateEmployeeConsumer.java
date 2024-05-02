package com.project.rabbitmq.consumer;

import com.project.dto.request.SaveEmployeeRequestDto;
import com.project.rabbitmq.model.CreateEmployeeModel;
import com.project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeConsumer {
    private final EmployeeService employeeService;

    @RabbitListener(queues = "manager-queue")
    public void createEmployeeListener(CreateEmployeeModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        employeeService.save(SaveEmployeeRequestDto.builder()
                .managerId(model.getManagerId())
                .name(model.getName())
                .surname(model.getSurname())
                .identityNumber(model.getIdentityNumber())
                .phoneNumber(model.getPhoneNumber())
                .address(model.getAddress())
                .position(model.getPosition())
                .department(model.getDepartment())
                .occupation(model.getOccupation())
                .build()
        );

    }
//TODO admin manager ı onaylayacak
//     add-employee da managerId?



}
