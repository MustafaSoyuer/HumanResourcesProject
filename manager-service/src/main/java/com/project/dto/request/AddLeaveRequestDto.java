package com.project.dto.request;

import com.project.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddLeaveRequestDto {
    // managerId, employeeId, izinbaşlangıç, izinsonu tarihleri,
    private String token;
    private Long managerId;
    private Long employeeId;
    private Long startDate;
    private Long endDate;
    private EStatus status;
    private Double numberOfDays;
    private String document;

}
