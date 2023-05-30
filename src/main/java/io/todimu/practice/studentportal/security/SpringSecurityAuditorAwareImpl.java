package io.todimu.practice.studentportal.security;

import io.todimu.practice.studentportal.utils.ResponseConstants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Optional<String> loggedInUser = getLoggedInUser();
        return Optional.of(loggedInUser.orElse(ResponseConstants.SYSTEM));
    }

    private Optional<String> getLoggedInUser() {
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
