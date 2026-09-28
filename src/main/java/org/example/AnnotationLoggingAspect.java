package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

/** Part A: the four core advice annotations. Not registered in AppConfig (replaced by AroundLoggingAspect). */
@Aspect
public class AnnotationLoggingAspect {

    @Before("execution(* org.example.InventoryServiceImpl.*(..))")
    public void before(JoinPoint jp) {
        System.out.println("@Before -> " + jp.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* org.example.InventoryServiceImpl.*(..))", returning = "result")
    public void afterReturning(JoinPoint jp, Object result) {
        System.out.println("@AfterReturning  -> " + jp.getSignature().getName() + " returned " + result);
    }

    @AfterThrowing(pointcut = "execution(* org.example.InventoryServiceImpl.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("@AfterThrowing   -> " + jp.getSignature().getName() + " threw " + ex);
    }

    @After("execution(* org.example.InventoryServiceImpl.*(..))")
    public void after(JoinPoint jp) {
        System.out.println("@After (finally) -> " + jp.getSignature().getName());
    }
}
