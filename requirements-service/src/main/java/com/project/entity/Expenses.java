package com.project.entity;

import com.project.utility.enums.EExpenseType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_expenses")
public class Expenses extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long employeeId;
    Long authId;
    Long managerId;
    Double amount;
    EExpenseType expenseType;
    String document;
    //TODO: Bunları date mi yapalım?
    Long requestDate;
    Long approvalDate;
}
