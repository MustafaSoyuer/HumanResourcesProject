package com.project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @Email
    private String identityNumber;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    @NotNull
    private String occupation;
    @NotNull
    private String department;
}
