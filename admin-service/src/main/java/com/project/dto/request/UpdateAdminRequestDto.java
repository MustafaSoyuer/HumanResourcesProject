package com.project.dto.request;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdminRequestDto {
    @Id
    private Long id;
    private String name;
    private String surname;
    private String address;
    @Size(min = 11, max = 15)
    private String phone;
    private String avatar;
}
