package com.mackenzie.cif.person.domain.repository;

import com.mackenzie.cif.person.domain.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> { }
