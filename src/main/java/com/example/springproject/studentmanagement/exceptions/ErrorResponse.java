package com.example.springproject.studentmanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private int status;
    private String message;
    private String details;

//    public ErrorResponse(LocalDateTime localDateTime, String message, String details) {
//        this.localDateTime = localDateTime;
//        this.message = message;
//        this.details = details;
//    }

}
