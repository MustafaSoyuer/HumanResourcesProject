package com.project.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CompanyGetAllResponseDto {

    private String name;
    private String title;
    private String description;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String logo;
    private String sector;
    private String country;
    private String city;
    private String employeeCount;
}
