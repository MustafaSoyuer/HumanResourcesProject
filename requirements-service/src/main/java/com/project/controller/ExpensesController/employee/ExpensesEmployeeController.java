package com.project.controller.ExpensesController.employee;


import com.project.dto.request.AddExpensesRequestDto;
import com.project.dto.response.AddExpensesResponseDto;
import com.project.dto.response.BasicResponse;
import com.project.entity.Expenses;
import com.project.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import  static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(EMPLOYEE)
@RequiredArgsConstructor
public class ExpensesEmployeeController {

    private final ExpensesService expensesService;


    @PostMapping(ADD_EXPENSE)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<AddExpensesResponseDto>> addExpenses(@RequestBody AddExpensesRequestDto dto) {
        return ResponseEntity.ok(BasicResponse.<AddExpensesResponseDto>builder()
                        .status(200)
                        .message("Expense is successfully added.")
                        .data(expensesService.addExpensesForEmployee(dto))
                .build());
    }

    @GetMapping(FIND_ALL_EXPENSES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<Expenses>>> findAllExpenses(@RequestParam String token) {
        return ResponseEntity.ok(BasicResponse.<List<Expenses>>builder()
                        .status(200)
                        .message("Expenses are successfully found.")
                        .data(expensesService.findAllExpensesForEmployee(token))
                .build());
    }
}
