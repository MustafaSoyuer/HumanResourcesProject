package com.project.dto.request;

import com.project.utility.enums.EExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApproveExpensesRequestDto {

    private Long id;
    private String token;
    private Long employeeId;
}
