package com.project.dto.request;

import com.project.utility.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveEmployeeRequestDto {

    private Long authId;
    private Long managerId; //TODO: model de istemiyoruz nasıl yapılacak. authtan geliyor getIdFromToken?
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
