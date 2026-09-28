package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class AroundLoggingAspect {

    @Around("execution(* org.example.InventoryServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().getName();
        System.out.println("around: before -> " + method);
        try {
            Object result = pjp.proceed();
            System.out.println("around: afterReturning -> " + method + " returned " + result);
            return result;
        } catch (Throwable ex) {
            System.out.println("around: afterThrowing  -> " + method + " threw " + ex);
            throw ex;
        } finally {
            System.out.println("around: after (finally)-> " + method);
        }
    }
}
