package com.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String authId;
    private String companyId;
    private String identityNumber;
    private String birthDate;
    private String email;
    private String phoneNumber;
    private String address;
    private String jobStartDate;
    private String jobEndDate;
    private String position;
    private String salary;
    private String department;
    private String occupation;
    private String gender;
    private String avatar;
    private String shiftId;

    private Long createAt;
    private Long updateAt;
    private boolean state;

}
