package com.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_SERVER_ERROR(5300, "Sunucu Hatasi",HttpStatus.INTERNAL_SERVER_ERROR),
    RENTING_ERROR(5301, "Kiralama gerçekleşmedi",HttpStatus.INTERNAL_SERVER_ERROR),
    RENTING_NOT_FOUND(2001, "Kiralama bulunamadı",HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_BALANCE(2002,"Yetersiz Bakiye", HttpStatus.BAD_REQUEST),
    BAD_REQUEST (4300,"Parametre hatasi", HttpStatus.BAD_REQUEST),
    VEHICLE_NOT_FOUND(4310,"Böyle bir kullanici bulunamadi...",HttpStatus.BAD_REQUEST),
    POST_NOT_FOUND(4312,"Böyle bir araç bulunamadi" ,HttpStatus.BAD_REQUEST),
    VEHICLE_ALREADY_EXISTS(4313,"Bu araç daha önceden kayıt edilmiştir." ,HttpStatus.BAD_REQUEST),
    VEHICLE_NOT_AVAILABLE(2002, "Bu araç kiralama için uygun değildir.",HttpStatus.BAD_REQUEST),
    VEHICLE_CANNOT_FOUND(2003, "Bu ID'de bir araç bulunamadı",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(2004, "Geçersiz token bilgisi girildi. Lütfen tekrar deneyiniz", HttpStatus.BAD_REQUEST);


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
