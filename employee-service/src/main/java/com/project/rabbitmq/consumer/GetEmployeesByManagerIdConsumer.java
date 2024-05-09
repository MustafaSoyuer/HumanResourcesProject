package com.project.rabbitmq.consumer;

import com.project.dto.request.GetEmployeesByManagerIdRequestDto;
import com.project.dto.response.GetEmployeesByManagerIdResponseDto;
import com.project.rabbitmq.model.GetEmployeesByManagerIdModel;
import com.project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEmployeesByManagerIdConsumer {

    private final EmployeeService employeeService;

    @RabbitListener(queues = "get-employees-by-manager-id-queue")
    public List<GetEmployeesByManagerIdResponseDto> getEmployeesByManagerId(GetEmployeesByManagerIdModel model) {
        System.out.println("Kuyruk gelen mesaj: " + model);
        return employeeService.getEmployeesByManagerId(GetEmployeesByManagerIdRequestDto.builder()
                    .managerId(model.getManagerId())
                .build()
        );
    }




}
