package com.project.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeModel implements Serializable {

    private Long managerId;
    private String name;
    private String surname;
    private String identityNumber;
    private String phoneNumber;
    private String address;
    private String position;
    private String department;
    private String occupation;
}
