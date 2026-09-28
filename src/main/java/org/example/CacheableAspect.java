package org.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Order(1)
public class CacheableAspect {

    private record Entry(Object value, long expiresAtMillis) {
        boolean expired() {
            return expiresAtMillis > 0 && System.currentTimeMillis() > expiresAtMillis;
        }
    }

    private final Map<List<Object>, Entry> cache = new ConcurrentHashMap<>();

    @Around("@annotation(cacheable)")
    public Object cache(ProceedingJoinPoint pjp, Cacheable cacheable) throws Throwable {
        String method = ((MethodSignature) pjp.getSignature()).getMethod().getName();
        String name = cacheable.cacheName().isEmpty() ? method : cacheable.cacheName();
        List<Object> key = List.of(name, Arrays.asList(pjp.getArgs()));

        Entry entry = cache.get(key);
        if (entry != null && !entry.expired()) {
            System.out.println("[cache] HIT  " + key);
            return entry.value();
        }

        System.out.println("[cache] MISS " + key);
        Object result = pjp.proceed();
        long expires = cacheable.ttlSeconds() > 0 ? System.currentTimeMillis() + cacheable.ttlSeconds() * 1000 : 0;
        cache.put(key, new Entry(result, expires));
        return result;
    }
}
