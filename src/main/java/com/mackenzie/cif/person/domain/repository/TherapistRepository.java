package com.mackenzie.cif.person.domain.repository;

import com.mackenzie.cif.person.domain.domain.Therapist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TherapistRepository extends JpaRepository<Therapist, Integer> {
    Optional<Therapist> findByCrefito(String crefito);
}
