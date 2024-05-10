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
public class BaseLeaveResponseDto {
    private Long id;
    private Long employeeId;
    private Long authId;
    private Long managerId;
    private String companyId;
    private Long startDate;
    private Long endDate;
    private Long approvalDate;
    private EStatus status;
    private Double numberOfDays;
    private String document;
}
