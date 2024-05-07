package com.project.dto.request;

import com.project.utility.enums.ELeaveType;
import com.project.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RejectLeaveRequestDto {
    private String token;
    private Long leaveId;
    private Long managerId;
    private Long employeeId;
    private Long startDate;
    private Long endDate;
    private EStatus status;
    private ELeaveType leaveType;
    private Double numberOfDays;
    private String document;
}
