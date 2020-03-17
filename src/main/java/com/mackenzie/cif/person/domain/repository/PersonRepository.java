package com.mackenzie.cif.person.domain.repository;

import com.mackenzie.cif.person.domain.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findAllByPatientNotNullAndActiveIsTrue();
    List<Person> findAllByPatientNullAndActiveIsTrue();

    Person findByIdAndActiveIsTrue(String id);
}
