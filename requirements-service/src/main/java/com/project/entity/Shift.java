package com.project.entity;

import com.project.utility.enums.EShiftType;
import com.project.utility.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_shift")
public class Shift extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private EShiftType shiftType;
    private String companyName;
    private Long managerId;
    private Long employeeId;
    private String employeeName;
    private String employeeSurname;
    private LocalDate startTime;
    private LocalDate endTime;
    private EStatus status;


}
