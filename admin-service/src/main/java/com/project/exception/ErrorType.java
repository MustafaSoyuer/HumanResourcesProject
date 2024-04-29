package com.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(9000, "Sunucuda beklenmeyen hata oluştu, lütfen tekrar deneyin", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_CREATE_TOKEN(9001, "Token oluşturma hatası, lütfen tekrar deneyiniz.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(9002,"Girilen parametreler hatalıdır, lütfen tekrar deneyin", HttpStatus.BAD_REQUEST);


    int code;
    String message;
    HttpStatus httpStatus;


}
