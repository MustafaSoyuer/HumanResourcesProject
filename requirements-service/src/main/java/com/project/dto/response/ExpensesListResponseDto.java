package com.project.dto.response;

import com.project.utility.enums.EExpenseType;
import com.project.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesListResponseDto {


    private Long id;
    private Long employeeId;
    private Long managerId;
    private EExpenseType expenseType;
    private Double amount;
    private EStatus status;
}
