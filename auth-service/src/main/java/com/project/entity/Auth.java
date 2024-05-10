package com.project.entity;

import com.project.utility.enums.ERole;
import com.project.utility.enums.EStatus;
import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_auth")
public class Auth  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @Size(min = 8,max = 64, message = "Sifre en az 8 karakterden oluşmalıdır.")
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private ERole role;

    private Long createAt;
    private Long updateAt;

    @Builder.Default
    private EStatus status=EStatus.PENDING;


}
