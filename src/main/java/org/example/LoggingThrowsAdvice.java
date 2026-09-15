package org.example;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class LoggingThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        System.out.println("Exception in " + method.getName() + ": " + ex.getMessage());
    }
}
