package com.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEmployeeRequestDto {

    private String token;
    private Long managerId;
    private String name;
    private String surname;
    private String identityNumber;
    private String phoneNumber;
    private String address;
    private String position;
    private String department;
    private String occupation;
    private String companyName;
}
