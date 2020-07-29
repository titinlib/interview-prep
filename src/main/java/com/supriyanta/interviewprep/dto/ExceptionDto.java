package com.supriyanta.interviewprep.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ExceptionDto {

    private Date timestamp;

    private Integer status;

    private String error;

    private String message;

    public ExceptionDto(String message, HttpStatus httpStatus) {
        this.timestamp = new Date();
        this.message = message;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }

    public ExceptionDto(String message) {
        this.timestamp = new Date();

        this.message = message;

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }

    public ExceptionDto() {
        this.timestamp = new Date();

        this.message = "No message available!";

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }
}
