package io.todimu.practice.studentportal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RateLimited {

}

// todo use AOP to check for rate limiting
// todo enable logging and monitoring with Treblle
// todo deploy application on render
// todo write github actions
