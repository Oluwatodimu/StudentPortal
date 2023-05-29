package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorityRepository extends JpaRepository<Authority, UUID> {

}
