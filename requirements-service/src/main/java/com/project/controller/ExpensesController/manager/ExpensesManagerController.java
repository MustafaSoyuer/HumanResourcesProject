package com.project.controller.ExpensesController.manager;

import com.project.dto.request.ApproveExpensesRequestDto;
import com.project.dto.request.BaseRequestExpensesDto;
import com.project.dto.request.BaseRequestForRequirementsDto;
import com.project.dto.response.BasicResponse;
import com.project.dto.response.ExpensesListResponseDto;
import com.project.entity.Expenses;
import com.project.service.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import  static com.project.utility.constants.RestApiUrls.*;

@RestController
@RequestMapping(MANAGER)
@RequiredArgsConstructor
public class ExpensesManagerController {

    private final ExpensesService expensesService;

    @PostMapping(APPROVE_EXPENSES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<Boolean>> approveExpenses(@RequestBody ApproveExpensesRequestDto dto){
        expensesService.approveExpensesForEmployee(dto);
        return ResponseEntity.ok(BasicResponse.<Boolean>builder()
                .status(200)
                .message("Expenses approved.")
                .data(true)
                .build());
    }

    @GetMapping(FIND_ALL_PENDING_EXPENSES)
    @CrossOrigin("*")
    public ResponseEntity<BasicResponse<List<ExpensesListResponseDto>>> findAllPendingExpenses(@RequestParam String token){
        return ResponseEntity.ok(BasicResponse.<List<ExpensesListResponseDto>>builder()
                .status(200)
                .data(expensesService.findAllPendingExpenses(token))
                .message("Pending expenses found.")
                .build());
    }


}
