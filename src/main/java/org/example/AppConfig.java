package org.example;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public LoggingBeforeAdvice loggingBeforeAdvice() {
        return new LoggingBeforeAdvice();
    }

    @Bean
    public LoggingAfterReturningAdvice loggingAfterReturningAdvice() {
        return new LoggingAfterReturningAdvice();
    }

    @Bean
    public LoggingThrowsAdvice loggingThrowsAdvice() {
        return new LoggingThrowsAdvice();
    }

    @Bean
    public LoggingMethodInterceptor loggingMethodInterceptor() {
        return new LoggingMethodInterceptor();
    }

    @Bean
    public InventoryServiceImpl inventoryServiceTarget() {
        return new InventoryServiceImpl();
    }

    @Bean
    public Advisor loggingBeforeAdvisor() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("reserveStock");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new LoggingBeforeAdvice());
        ProxyFactory factory = new ProxyFactory();
        return advisor;
    }

    @Bean
    public ProxyFactoryBean inventoryService() {

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(inventoryServiceTarget());
        proxyFactoryBean.setInterfaces(InventoryService.class);
        proxyFactoryBean.addAdvisor(loggingBeforeAdvisor());
        proxyFactoryBean.addAdvice(loggingAfterReturningAdvice());
        proxyFactoryBean.addAdvice(loggingThrowsAdvice());
        proxyFactoryBean.addAdvice(loggingMethodInterceptor());
        return proxyFactoryBean;
    }
}
