package org.example;

import org.springframework.aop.framework.ProxyFactory;
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
    public InventoryService inventoryService() {
        ProxyFactory proxyFactory = new ProxyFactory(new InventoryServiceImpl());
        proxyFactory.addAdvice(loggingBeforeAdvice());
        proxyFactory.addAdvice(loggingAfterReturningAdvice());
        proxyFactory.addAdvice(loggingThrowsAdvice());
        proxyFactory.addAdvice(loggingMethodInterceptor());
        return (InventoryService) proxyFactory.getProxy();
    }
}
