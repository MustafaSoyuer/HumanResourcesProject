package com.project.entity;

import com.project.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authId;
    private String name;
    private String surname;
    private String avatar;
    private String birthDate;
    private String phone;
    private String identityNumber;
    private String taxNumber;
    private String email;
    private String address;
    private Long occupation;
    private String companyId;
    private String gender;
    private String jobStartDate;
    private Long createAt;
    private Long updateAt;
    private EStatus status; // TODO onaylanması admin tarafından yapılacak (sadece auth ta mı olacak ? aynı zaman durumu burada da görmeliyim)

}
