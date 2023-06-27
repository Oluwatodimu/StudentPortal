package io.todimu.practice.studentportal.annotation;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.todimu.practice.studentportal.exception.RateLimitException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
//public class RateLimitingAspect {
//
//    private final RateLimiter rateLimiter;
//
//    public RateLimitingAspect(RateLimiter rateLimiter) {
//        this.rateLimiter = rateLimiter;
//    }
//
//    @Before("@within(com.example.RateLimited) || @annotation(com.example.RateLimited)")
//    public void applyRateLimit() {
//        if (!rateLimiter.acquirePermission()) {
//            throw new RateLimitException("Rate limit exceeded");
//        }
//    }
//}
