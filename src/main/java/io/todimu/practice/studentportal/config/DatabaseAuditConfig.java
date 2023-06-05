package io.todimu.practice.studentportal.config;

import io.todimu.practice.studentportal.repository.UserRepository;
import io.todimu.practice.studentportal.security.SpringSecurityAuditorAwareImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("io.todimu.practice.studentportal.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAwareImpl")
public class DatabaseAuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAwareImpl();
    }
}
