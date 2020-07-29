package com.supriyanta.interviewprep.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ResponseDto<T> {
    private T body;

    private Date timestamp;

    private Integer status;

    private String message;

    public ResponseDto() {
        this.timestamp = new Date();

        this.body = (T) "Not available";

        HttpStatus httpStatus = HttpStatus.OK;

        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public ResponseDto(T body) {
        this.timestamp = new Date();
        this.body = body;

        HttpStatus httpStatus = HttpStatus.OK;

        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public ResponseDto(HttpStatus httpStatus) {
        this.timestamp = new Date();
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();

        this.body = (T) "Not available";
    }

    public ResponseDto(T body, HttpStatus httpStatus) {
        this.timestamp = new Date();
        this.body = body;
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }
}
