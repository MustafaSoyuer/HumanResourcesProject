package com.project.rabbitmq.model;

import com.project.utility.enums.ERole;
import com.project.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserModel implements Serializable {

    private Long authId;
    private String email;
    private String password;
    private ERole role;
    private EStatus state;

}
