package com.example.system.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectsLog {

    @Before("within(com.example.system.controller.impl.*)")
    public void printControllerBefore(JoinPoint joinPoint) throws Exception {

        log.info("[{}] {} - {}",
                "START",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
    }

    @After("within(com.example.system.controller.impl.*)")
    public void printControllerAfter(JoinPoint joinPoint) throws Exception {
        log.info("[{}] {} - {}",
                "END",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
    }

    @Before("within(com.example.system.domain.service.impl.*)")
    public void printServiceBefore(JoinPoint joinPoint) throws Exception {
        log.info("[{}] {} - {}",
                "START",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
    }

    @After("within(com.example.system.domain.service.impl.*)")
    public void printServiceAfter(JoinPoint joinPoint) throws Exception {
        log.info("[{}] {} - {}",
                "END",
                joinPoint.getSignature().getDeclaringType().getSimpleName(),
                ((MethodSignature) joinPoint.getSignature()).getMethod().getName());
    }

}
