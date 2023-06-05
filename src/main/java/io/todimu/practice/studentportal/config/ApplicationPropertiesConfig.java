package io.todimu.practice.studentportal.config;

import io.todimu.practice.studentportal.config.properties.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationPropertiesConfig {

    @Bean
    @ConfigurationProperties(prefix = "redis", ignoreUnknownFields = false)
    public RedisProperties redisProperties() {
        return new RedisProperties();
    }
}
