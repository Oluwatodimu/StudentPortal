package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.dto.ActivationKey;

public interface ActivationKeyRepository {

    ActivationKey save(ActivationKey key);

    ActivationKey findByActivationKey(String activationKey);
}
