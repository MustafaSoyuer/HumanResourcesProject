package com.project.dto.request;

import com.project.utility.enums.EMemberShipPlan;
import com.project.utility.enums.EStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CompanyUpdateRequestDto {

    private String id;
    private String token;
    private Long managerId;
    private String name;
    private String title;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String sector;
    private String taxNumber;
    private String employeeCount;

}
