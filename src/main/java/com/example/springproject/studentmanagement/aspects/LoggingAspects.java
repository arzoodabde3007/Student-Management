package com.example.springproject.studentmanagement.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspects {

    @Before("execution(* com.example.springproject.studentmanagement.service.StudentService.addStudent(..))")
    public void logger(){
        System.out.println("Logger Aspect call");
    }
}
