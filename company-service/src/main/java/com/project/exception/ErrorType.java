package com.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(1000, "Sunucuda beklenmeyen hata oluştu, lütfen tekrar deneyin", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_DUPLICATE_USERNAME(2000, "Bu kullanıcı adı zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.", HttpStatus.BAD_REQUEST),
    ERROR_INVALID_LOGIN_PARAMETER(2001, "Girilen kullanıcı adı ya da parola hatalıdır. Lütfen tekrar deneyiniz", HttpStatus.BAD_REQUEST),
    ERROR_CREATE_TOKEN(1003, "Token oluşturma hatası, lütfen tekrar deneyiniz.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"Girilen parametreler hatalıdır, lütfen tekrar deneyin", HttpStatus.BAD_REQUEST);


    int code;
    String message;
    HttpStatus httpStatus;


}
