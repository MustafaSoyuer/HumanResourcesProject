package com.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequestExpensesDto {

    private Long requirementId;
    private Long employeeId;
    private String token;



}
