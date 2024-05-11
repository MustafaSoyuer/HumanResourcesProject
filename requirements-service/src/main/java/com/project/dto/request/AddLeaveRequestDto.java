package com.project.dto.request;

import com.project.utility.enums.ELeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddLeaveRequestDto {
    private String token;
    private Long leaveId;
    private Long managerId;
    private Long employeeId;
    private Long startDate;
    private Long endDate;
    private ELeaveType leaveType;
    private String document;
}
