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
public class UpdateManagerRequestDto {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String avatar;
    private String birthDate;
    private String phone;
    private String taxNumber;
    private String address;
    private String gender;
}
