package com.project.service;

import com.project.dto.request.AddExpensesRequestDto;
import com.project.dto.request.ApproveExpensesRequestDto;
import com.project.dto.request.BaseRequestExpensesDto;

import com.project.dto.response.AddExpensesResponseDto;
import com.project.dto.response.EmployeeResponseDto;
import com.project.dto.response.ExpensesListResponseDto;
import com.project.dto.response.ManagerResponseDto;
import com.project.entity.Expenses;
import com.project.exception.ErrorType;
import com.project.exception.RequirementsServiceException;
import com.project.manager.EmployeeManager;
import com.project.manager.ManagerManager;
import com.project.mapper.ExpensesMapper;
import com.project.repository.ExpensesRepository;
import com.project.utility.enums.ERole;
import com.project.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final EmployeeManager employeeManager;
    private final ManagerManager managerManager;
    private final ExpensesMapper expensesMapper;

    public List<Expenses> findAllExpensesForEmployee(String token) {
        EmployeeResponseDto employee = Optional.ofNullable(employeeManager.findEmployeeByToken(token).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));

        List<Expenses> expenses = expensesRepository.findAllByEmployeeId(employee.getId());

        if (expenses.isEmpty()) {
            throw new RequirementsServiceException(ErrorType.EXPENSES_NOT_FOUND);
        }
        return expenses;
    }

    public AddExpensesResponseDto addExpensesForEmployee(AddExpensesRequestDto dto) {
        EmployeeResponseDto employee = Optional.ofNullable(employeeManager.findEmployeeByToken(dto.getToken()).getBody())
                .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));
        return ExpensesMapper.INSTANCE.fromExpensesToAddExpensesResponseDto(expensesRepository.save(Expenses.builder()
                .employeeId(employee.getId())
                .managerId(employee.getManagerId())
                .amount(dto.getAmount())
                .expenseType(dto.getExpenseType())
                .description(dto.getDescription())
                .requestDate(System.currentTimeMillis())
                .status(EStatus.PENDING)
                .build()));

    }

    public Boolean approveExpensesForEmployee(ApproveExpensesRequestDto dto) {
        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(dto.getToken()).getBody())
                .orElseThrow(() -> new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));
        Optional<Expenses> expensesOptional = expensesRepository.findById(dto.getId());
        if (expensesOptional.isPresent()) {
            Expenses expense = expensesOptional.get();
                if (expense.getStatus().equals(EStatus.PENDING)) {
                    expense.setStatus(EStatus.ACTIVE);
                    expense.setApprovalDate(System.currentTimeMillis());
                    expensesRepository.save(expense);
                    // Harcama miktarını çalışanın maaşına ekler
                    EmployeeResponseDto employee = Optional.ofNullable(employeeManager.findById(dto.getEmployeeId()).getBody())
                            .orElseThrow(()->new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND));
                    if (employee != null ) {
                            double currentSalary = employee.getSalary() != null ? employee.getSalary() : 0.0;
                            double additionalAmount = expense.getAmount();
                            double newSalary = currentSalary + additionalAmount;
                            employee.setSalary(newSalary);
                            employeeManager.updateEmployeeSalary(employee.getId(), newSalary);
                            return true;

                    } else {
                        throw new RequirementsServiceException(ErrorType.EMPLOYEE_NOT_FOUND);
                    }
                }
        } else {
            throw new RequirementsServiceException(ErrorType.EXPENSES_NOT_FOUND);
        }
        return true;
    }

    public List<ExpensesListResponseDto> findAllPendingExpenses(String token) {
        ManagerResponseDto manager = Optional.ofNullable(managerManager.findByToken(token).getBody())
                .orElseThrow(() -> new RequirementsServiceException(ErrorType.MANAGER_NOT_FOUD));
            return expensesRepository.findAll().stream().
                    filter(expenses -> expenses.getStatus().equals(EStatus.PENDING))
                    .map(expensesMapper::fromExpensesToExpensesListResponseDto)
                    .collect(Collectors.toList());
    }
}
