package com.project.dto.request;

import com.project.utility.enums.EExpenseType;
import com.project.utility.enums.ELeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddExpensesRequestDto {

    private String token;
    private Double amount;
    private EExpenseType expenseType;
    private String description;


}
