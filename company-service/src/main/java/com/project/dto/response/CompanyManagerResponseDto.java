package com.project.dto.response;

import com.project.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CompanyManagerResponseDto {

    private String id;
    private Long managerId;
    private String name;
    private String taxNumber;
    private EStatus status;

}
