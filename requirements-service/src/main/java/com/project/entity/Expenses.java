package com.project.entity;

import com.project.utility.enums.EExpenseType;
import com.project.utility.enums.EStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private Long managerId;
    private Double amount;
    private EExpenseType expenseType;
    private String description;
    private Long requestDate;
    private Long approvalDate;
    private EStatus status;
}
