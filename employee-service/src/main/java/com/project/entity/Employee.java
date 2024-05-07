package com.project.entity;

import com.project.utility.EStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    private Long authId;
    private Long managerId;
    private String companyId;
    @Column(unique = true)
    private String identityNumber;
    private String birthDate;
    @Email
    //TODO: email kontrolu ekle wp dan
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
    private EStatus status;

    private Long createAt;
    private Long updateAt;


}
