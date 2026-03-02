package com.example.springproject.studentmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(StudentNotFoundException.class)
//    public ResponseEntity<Map<String, Object>> handleStudentNotFoundException(StudentNotFoundException exception){
//        Map<String, Object> errorResponse = new HashMap<>();
//
//        errorResponse.put("timestamp : ", LocalDateTime.now());
//        errorResponse.put("status : ", HttpStatus.NOT_FOUND.value());
//        errorResponse.put("error : ", "Not Found");
//        errorResponse.put("message : ", exception.getMessage());
//
//        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(
          LocalDateTime.now(),
          HttpStatus.NOT_FOUND.value(),
                "NOT Found",
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException exception
    ){
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(
                        error -> errors.put(error.getField(), error.getDefaultMessage())
                );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler()
//    public ResponseEntity< >
}
