package io.todimu.practice.studentportal.security;

import io.todimu.practice.studentportal.repository.UserRepository;
import io.todimu.practice.studentportal.utils.ResponseConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SpringSecurityAuditorAwareImpl implements AuditorAware<String> {

    public static final String ANONYMOUS_USER = "anonymousUser";

    private final UserRepository userRepository;

    @Override
    public Optional<String> getCurrentAuditor() {
        Optional<String> loggedInUser = getLoggedInUser();
        return Optional.of(loggedInUser.orElse(ResponseConstants.SYSTEM));
    }

    private Optional<String> getLoggedInUser() {
        String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.equals(ANONYMOUS_USER)) {
            return Optional.of(ANONYMOUS_USER);
        }

        String userId = userRepository.findByEmail(principal).get().getUserId().toString();
        return Optional.of(userId);
    }
}
