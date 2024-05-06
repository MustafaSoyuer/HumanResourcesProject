package com.project.entity;

import com.project.utility.enums.ERole;
import com.project.utility.enums.EStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;


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
    private String email;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private ERole role;

    private Long createAt;
    private Long updateAt;

    @Builder.Default
    private EStatus status=EStatus.PENDING; //TODO methodlara ekle
    private String activationCode;

}
