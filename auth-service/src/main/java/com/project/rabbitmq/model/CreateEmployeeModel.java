package com.project.rabbitmq.model;


import com.project.utility.enums.EStatus;
import lombok.*;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeModel implements Serializable {

    private Long authId;
    private String name;
    private String surname;
    private String identityNumber;
    private String phoneNumber;
    private String email;
    private String address;
    private String position;
    private String department;
    private String occupation;
    private String companyName;
    private EStatus status;
}
