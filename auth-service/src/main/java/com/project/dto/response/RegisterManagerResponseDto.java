package com.project.dto.response;

import com.project.utility.enums.ERole;
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
public class RegisterManagerResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String company;
    private String taxNumber;
}
