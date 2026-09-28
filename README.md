# spring-aop-proxy-lab

A hands-on lab for classic Spring AOP using `ProxyFactoryBean`, advisors, and pointcuts (NTI – Spring AOP, Day 2).

@ Instructor : Mohamed Ezz

## What it demonstrates

- **Before advice** – `LoggingBeforeAdvice`, applied only to `reserveStock` through a `NameMatchMethodPointcut` + `DefaultPointcutAdvisor`
- **After-returning advice** – `LoggingAfterReturningAdvice`
- **Throws advice** – `LoggingThrowsAdvice`
- **Around advice** – `LoggingMethodInterceptor` (AOP Alliance `MethodInterceptor`)
- **Proxy creation** – `ProxyFactoryBean` wrapping `InventoryServiceImpl` behind the `InventoryService` interface

## Project structure

```
src/main/java/org/example/
├── AppConfig.java                    # Beans, advisor, ProxyFactoryBean
├── InventoryService.java             # Service interface
├── InventoryServiceImpl.java         # Target object
├── LoggingBeforeAdvice.java
├── LoggingAfterReturningAdvice.java
├── LoggingThrowsAdvice.java
├── LoggingMethodInterceptor.java
└── Main.java                         # Entry point
```

## Run

```bash
mvn compile exec:java -Dexec.mainClass=org.example.Main
```

Or run `Main` directly from your IDE. The demo calls `checkStock("SKU-123")` and `reserveStock("SKU-123", 5)`; uncomment the `reserveStock("SKU-123", 101)` line in `Main` to trigger the throws advice.

## Tech

Java · Spring Framework (AOP, Context) · Maven
