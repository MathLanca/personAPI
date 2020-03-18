package com.mackenzie.cif.person.domain.repository;

import com.mackenzie.cif.person.domain.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

    /*
    * @return All patients who is active on data base
    */
    List<Person> findAllByPatientNotNullAndActiveIsTrue();
    /*
     * @return All therapists who is active on data base
     */
    List<Person> findAllByPatientNullAndActiveIsTrue();

    /*
     * @param id
     * @return specific patient or therapist related to param id
     */
    Person findByIdAndActiveIsTrue(String id);

    /*
     * @param email
     * @return specific person based on email
     */
    Person findByEmail(String email);

    /*
     * @param cpf
     * @return specific person based on cpf
     */
    Person findByCpf(String email);

    /*
     * @param email
     * @param password
     * @return true if exists a person with these params or false if not
     */
    Boolean existsByEmailAndPassword(String email, String password);

    /*
     * @param cpf
     * @param password
     * @return true if exists a person with these params or false if not
     */
    Boolean existsByCpfAndPassword(String cpf, String password);
}
