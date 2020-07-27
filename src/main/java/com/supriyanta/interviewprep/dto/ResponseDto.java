package com.supriyanta.interviewprep.dto;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ResponseDto<T> {
    private T body;

    private final Date timestamp;

    private HttpStatus httpStatus;

    public ResponseDto() {
        timestamp = new Date();
    }

    public ResponseDto(T body) {
        this.body = body;
        timestamp = new Date();
    }

    public ResponseDto(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        timestamp = new Date();
    }

    public ResponseDto(T body, HttpStatus httpStatus) {
        this.body = body;
        this.httpStatus = httpStatus;
        timestamp = new Date();
    }
}