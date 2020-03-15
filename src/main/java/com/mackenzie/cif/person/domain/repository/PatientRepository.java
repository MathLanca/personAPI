package com.mackenzie.cif.person.domain.repository;

import com.mackenzie.cif.person.domain.domain.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    List<Patient> findAllByActive(Boolean active);

    Boolean existsByPersonEmailAndPersonPassword(String email, String password);

    Patient findByPersonEmail(String email);
}


