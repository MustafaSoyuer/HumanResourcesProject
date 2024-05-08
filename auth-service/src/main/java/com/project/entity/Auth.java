package com.project.entity;

import com.project.utility.enums.ERole;
import com.project.utility.enums.EStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @Column(unique = true)
    private String email;
    @Size(min = 8,max = 64, message = "Sifre en az 8 karakterden oluşmalıdır.")
    @Pattern(regexp = "(?=.\\d)(?=.[a-z])(?=.[A-Z])(?=.[!*?.]).{8,64}$"
            ,message = "En az bir büyük harf, en az bir küçük harf, en az bir rakam ve [*!?.]'dan birisini kullanmalısınız." )
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private ERole role;

    private Long createAt;
    private Long updateAt;

    @Builder.Default
    private EStatus status=EStatus.PENDING;


}
