package com.project.dto.request;

import com.project.utility.enums.EEmployeeChooseLeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestLeaveDto {
    private String token;
    private Long startDate;
    private Long endDate;
    private EEmployeeChooseLeaveType leaveType;
    private String document;
}
