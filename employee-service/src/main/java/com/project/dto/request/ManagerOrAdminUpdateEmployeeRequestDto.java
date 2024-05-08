package com.project.dto.request;

import com.project.utility.EStatus;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerOrAdminUpdateEmployeeRequestDto {

    private Long id;
    private String token;
    private Long managerId;
    private String name;
    private String surname;
    private String birthDate;
    private String phoneNumber;
    private String address;
    private Boolean militaryService;
    private String driverLicense;
    private String jobStartDate;
    private String jobEndDate;
    private String position;
    private String salary;
    private String department;
    private String occupation;
    private String avatar;
    private String shiftId;
    private EStatus status;




}
