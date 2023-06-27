package io.todimu.practice.studentportal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimited {

}

// todo enable logging and monitoring with Treblle (bug)
// todo deploy application on render
// todo write github actions
