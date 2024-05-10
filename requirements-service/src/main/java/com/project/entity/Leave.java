package com.project.entity;

import com.project.utility.enums.ELeaveType;
import com.project.utility.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_leave")
public class Leave extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private Long authId;
    private Long managerId;
    private String companyId;
    private String employeeName;
    private String employeeSurname;
    private Long startDate;
    private Long endDate;
    private Long approvalDate;
    private EStatus status;
    private ELeaveType leaveType;
    private Double numberOfDays;
    private String document;
}
