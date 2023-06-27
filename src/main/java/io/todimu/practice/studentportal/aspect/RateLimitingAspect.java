package io.todimu.practice.studentportal.aspect;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.todimu.practice.studentportal.exception.RateLimitException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimitingAspect {

    private final RateLimiter rateLimiter;

    @Before("@annotation(io.todimu.practice.studentportal.annotation.RateLimited)")
    public void applyRateLimit() {
        if (!rateLimiter.acquirePermission()) {
            throw new RateLimitException("too many requests");
        }
    }
}
