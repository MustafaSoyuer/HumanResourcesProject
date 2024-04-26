package com.project.entity;

import com.project.utility.enums.EStatus;
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
@Table(name = "tbl_leave")
public class Leave extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long employeeId;
    Long authId;
    Long managerId;
    String companyId;
    String employeeName;
    String employeeSurname;
    //TODO: Dateler değişsin mi?
    Long startDate;
    Long approvalDate;
    EStatus status;
    Double numberOfDays;
    String document;
}
