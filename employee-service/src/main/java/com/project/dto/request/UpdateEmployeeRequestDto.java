package com.project.dto.request;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequestDto {
    @Id
    private Long employeeId;
    private String token;
    private String name;
    private String surname;
    private String birthDate;
    private String phoneNumber;
    private String address;
    private String avatar;

}
