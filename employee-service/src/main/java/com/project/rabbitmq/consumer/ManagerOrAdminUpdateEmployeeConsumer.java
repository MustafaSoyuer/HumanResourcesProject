package com.project.rabbitmq.consumer;

import com.project.dto.request.ManagerOrAdminUpdateEmployeeRequestDto;
import com.project.rabbitmq.model.ManagerOrAdminUpdateEmployeeModel;
import com.project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerOrAdminUpdateEmployeeConsumer {
    private final EmployeeService employeeService;

    @RabbitListener(queues = "manager-or-admin-update-employee-queue")
    public void updateEmployeeListener(ManagerOrAdminUpdateEmployeeModel model){
        System.out.println("Kuyruk gelen mesaj: " + model);
        employeeService.managerOrAdminUpdateEmployee(ManagerOrAdminUpdateEmployeeRequestDto.builder()
                        .id(model.getId())
                        .managerId(model.getManagerId())
                        .name(model.getName())
                        .surname(model.getSurname())
                        .birthDate(model.getBirthDate())
                        .phoneNumber(model.getPhoneNumber())
                        .address(model.getAddress())
                        .jobStartDate(model.getJobStartDate())
                        .jobEndDate(model.getJobEndDate())
                        .position(model.getPosition())
                        .salary(model.getSalary())
                        .department(model.getDepartment())
                        .occupation(model.getOccupation())
                        .avatar(model.getAvatar())
                        .shiftId(model.getShiftId())
                        .status(model.getStatus())
                        .build());

    }
}
