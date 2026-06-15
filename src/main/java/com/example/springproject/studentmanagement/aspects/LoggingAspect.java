package com.example.springproject.studentmanagement.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log =
            LoggerFactory.getLogger(LoggingAspect.class);
    @Before("execution(* com.example.springproject.studentmanagement.service.*.*(..))")
    public void logger(){
        log.info("Service Method called...");
    }

}
