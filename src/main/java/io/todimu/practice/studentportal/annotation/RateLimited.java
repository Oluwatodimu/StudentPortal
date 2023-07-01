package io.todimu.practice.studentportal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimited {

}

// method limiting -> need to add Allow header to the responses
// security policies -> have a Content-Security-Policy header defined
// strict-transport-security -> strict-transport-security header in your API responses
// Content Type Specification -> Accept header exists and its value is set to application/json.
// Rate Limiting -> x-ratelimit-* headers in the response payload to see if you correctly use rate limiting
// Secure Connection -> Treblle automatically detects whether or not the requests your end customers are making are using HTTPs instead of HTTP.
// IP reputation -> own network as well as 3rd party services to check the reputation of your users IP address
// SQL injection