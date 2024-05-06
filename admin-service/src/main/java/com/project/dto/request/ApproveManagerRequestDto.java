package com.project.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApproveManagerRequestDto {

    private String token;
    private Long authId;
    private String name;
    private String surname;
    private String company;
    private String taxNumber;

}
