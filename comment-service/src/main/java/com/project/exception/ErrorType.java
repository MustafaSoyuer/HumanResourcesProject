package com.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {
    INTERNAL_ERROR(1000, "Sunucuda beklenmeye hata oluştu, lütfen terar deneyiniz",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001, "Girilen parametreler hatalıdır. Lütfen düzelterek tekrar deneyiniz", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1002,"Girdiğiniz token hatalıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.UNAUTHORIZED),
    MANAGER_ALREADY_HAVE_COMMENT(1003,"Bu kullanıcı zaten bir yorum ekledi.",HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;
}
