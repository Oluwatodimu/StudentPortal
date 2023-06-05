package io.todimu.practice.studentportal.config;

import io.todimu.practice.studentportal.security.filter.JwtValidationFilter;
import io.todimu.practice.studentportal.security.jwt.JwtTokenProvider;
import io.todimu.practice.studentportal.utils.AuthoritiesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .cors().configurationSource(
                        request -> {
                            CorsConfiguration configuration = new CorsConfiguration();
                            configuration.setAllowedOrigins(Collections.singletonList("*"));
                            configuration.setAllowedMethods(Collections.singletonList("*"));
                            configuration.setAllowCredentials(true);
                            configuration.setAllowedHeaders(Collections.singletonList("*"));
                            configuration.setExposedHeaders(List.of(AuthoritiesConstants.AUTHORITIES_HEADER));
                            configuration.setMaxAge(3600L);
                            return configuration;
                        })
                .and()
                .addFilterBefore(new JwtValidationFilter(jwtTokenProvider), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/student/register").permitAll()
                .requestMatchers("/api/v1/student/activate").permitAll()
                .requestMatchers("/api/v1/student/retrieve/{value}").authenticated()
                .requestMatchers("/api/v1/student/retrieve").authenticated()
                .requestMatchers("/api/v1/student/update").authenticated()
                .requestMatchers("/api/v1/teacher/register").authenticated()
                .requestMatchers("/api/v1/user/authenticate").permitAll()
                .requestMatchers("/api/v1/course").authenticated()
                .requestMatchers("/api/v1/course-registration").authenticated()
                .requestMatchers("/api/v1/user/say-hi").authenticated()
        ;

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
