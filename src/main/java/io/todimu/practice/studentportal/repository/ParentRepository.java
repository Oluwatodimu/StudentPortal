package io.todimu.practice.studentportal.repository;

import io.todimu.practice.studentportal.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, UUID> {

}
