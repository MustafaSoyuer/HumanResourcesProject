package com.project.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String identityNumber;
    private String phoneNumber;
    private String email;
    private String address;
    private String avatar;

}
