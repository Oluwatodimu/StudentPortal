package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.model.Authority;
import io.todimu.practice.studentportal.model.User;
import io.todimu.practice.studentportal.repository.AuthorityRepository;
import io.todimu.practice.studentportal.utils.AuthoritiesConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Transactional
    public void createStudentAuthorities(User user) {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(Authority.builder().user(user).role(AuthoritiesConstants.STUDENT).build());
        authorities.forEach(authority -> authority.setUser(user));
        authorityRepository.saveAll(authorities);
    }
}
