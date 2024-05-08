package com.project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequestDto {
    @Email
    @NotNull
    private String email;

    //TODO: validation dependencies nerede?
    @Size(min=8, max=20)
    @NotNull
    private String password;
}
