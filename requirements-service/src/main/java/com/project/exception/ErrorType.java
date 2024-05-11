package com.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_SERVER_ERROR(5300, "Sunucu Hatasi",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN(4001,"Invalid token information",HttpStatus.BAD_REQUEST),
    NO_LEAVES_FOR_AN_EMPLOYEE(2002,"There is no leave for an employee",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(1202,"You have entered an invalid parameter",HttpStatus.BAD_REQUEST),
    ADD_LEAVE_ERROR(5301, "Kiralama gerçekleşmedi",HttpStatus.INTERNAL_SERVER_ERROR),
    LEAVE_NOT_FOUND(2001, "Leave cannot found.",HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_FOUND(2001, "Employee cannot found.",HttpStatus.BAD_REQUEST),
    LEAVE_TYPE_NOT_FOUND(2001, "Leave type cannot found.",HttpStatus.BAD_REQUEST),
    LEAVE_DATE_NOT_VALID(2001, "Leave date is not valid.",HttpStatus.BAD_REQUEST),
    NO_PENDING_LEAVES_FOR_MANAGER(2002, "There is no pending leave for manager.",HttpStatus.BAD_REQUEST),
    MANAGER_NOT_FOUD(2001, "Manager cannot found.",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
