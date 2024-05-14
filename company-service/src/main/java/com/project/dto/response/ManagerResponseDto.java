package com.project.dto.response;

import com.project.utility.enums.ERole;
import com.project.utility.enums.EStatus;
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
    private Long authId;
    private String companyId;
    private String name;
    private String surname;
    private String email;
    private String identityNumber;
    private String taxNumber;
    private Long occupation;
    private String avatar;
    private Long updateAt;
    private ERole role;
    private EStatus status;
}
