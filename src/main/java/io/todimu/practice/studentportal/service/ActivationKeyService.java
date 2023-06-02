package io.todimu.practice.studentportal.service;

import io.todimu.practice.studentportal.dto.ActivationKey;
import io.todimu.practice.studentportal.repository.ActivationKeyRepository;
import io.todimu.practice.studentportal.utils.ValueGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivationKeyService {

    private final ActivationKeyRepository activationKeyRepository;

    public ActivationKey createActivationKey(String userId) {
        String activationKey = ValueGenerator.generateActivationKey();

        ActivationKey keyJson = ActivationKey.builder()
                .activationKey(activationKey)
                .userId(userId)
                .build();

        keyJson = activationKeyRepository.save(keyJson);
        return keyJson;
    }

    public ActivationKey findByActivationKey(String activationKey) {
        return activationKeyRepository.findByActivationKey(activationKey);
    }
}
