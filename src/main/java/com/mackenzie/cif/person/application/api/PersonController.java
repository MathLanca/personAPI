package com.mackenzie.cif.person.application.api;

import com.mackenzie.cif.person.application.conversor.PersonConversor;
import com.mackenzie.cif.person.common.ValidadorCpf;
import com.mackenzie.cif.person.domain.domain.Person;
import com.mackenzie.cif.person.domain.dto.PersonRequest;
import com.mackenzie.cif.person.domain.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/person")
@Slf4j
public class PersonController {

    @Autowired
    private PersonService service;

    @CrossOrigin("*")
    @PostMapping("/register")
    public ResponseEntity<?> registerPerson(@RequestBody PersonRequest body) {
        log.info("Register person started >>>>>");
        Person therapist;
        Person person;

        if (body.getCpf() == null || !ValidadorCpf.isCPF(body.getCpf())) {
            log.error("Invalid cpf!");
            return new ResponseEntity<>("Please enter a valid CPF", HttpStatus.FORBIDDEN);
        }

        person = PersonConversor.personRequestToPerson(body);
        if (body.getPatient() != null && body.getPatient().getTherapistID() != null) {
            therapist = service.findPersonById(body.getPatient().getTherapistID());
            if (therapist != null) {
                person.getPatient().setTherapist(therapist);
            } else {
                log.error("Could not find Therapist for");
                return new ResponseEntity<>("Could not register the person", HttpStatus.NOT_MODIFIED);
            }
        }
        try {
            service.registerPerson(person);
        } catch (DuplicateKeyException e) {
            log.error("Could not register person, email already registered");
            log.error(e.getMessage());
            if(Objects.requireNonNull(e.getMessage()).contains("cpf"))
                return new ResponseEntity<>("CPF_ALREADY_REGISTERED", HttpStatus.FORBIDDEN);
            return new ResponseEntity<>("EMAIL_ALREADY_REGISTERED", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            log.error("Could not register the person");
            log.error(e.getMessage());
            return new ResponseEntity<>("Could not register the person", HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin("*")
    @GetMapping("/listAllPatient")
    public ResponseEntity<?> listAllPatient() {
        List<Person> patients;
        try {
            patients = service.listAllPatient();
        } catch (Exception e) {
            return new ResponseEntity<>("Could not retrieve list of patients", HttpStatus.OK);
        }


        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/listAllTherapist")
    public ResponseEntity<?> listAllTherapist() {
        List<Person> therapists;
        try {
            therapists = service.listAllTherapist();
        } catch (Exception e) {
            return new ResponseEntity<>("Could not retrieve list of therapists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(therapists, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findPatientsByTherapist/{id}")
    public ResponseEntity<?> findPatientsByTherapist(@PathVariable String id) {
        log.info("findPatientsByTherapist>>>>>");

        List<Person> patients;
        try {
            patients = service.findPatientsByTherapist(id);
        } catch (Exception e) {
            log.error("Error while finding patients from therapist " + id, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (patients == null) {
            String notFound = "NO_PATIENTS_FOR_THERAPIST";
            return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findById/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable String id) {
        log.info("Find person by id started >>>>>");

        Person person;
        try {
            person = service.findPersonById(id);
        } catch (Exception e) {
            log.error("Error while finding person with ID " + id, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (person == null) {
            String notFound = "Could not find person";
            return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updatePerson/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable String id, @RequestBody @Valid Person body) {
        log.info("Update person started >>>>>");
        if (body.getCpf() == null || !ValidadorCpf.isCPF(body.getCpf())) {
            log.error("Invalid cpf!");
            return new ResponseEntity<>("Please enter a valid CPF", HttpStatus.FORBIDDEN);
        }
        body.setId(id);
        Person response;
        try {
            response = service.updatePerson(body, id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Could not update person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response == null) {
            return new ResponseEntity<>("Could not found person", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @PutMapping("/updatePassword/{cpf}")
    public ResponseEntity<?> updatePassword(@RequestHeader @Valid String password, @PathVariable String cpf) {
        log.info("Update password started >>>>>");
        if (password == null || password.equals("")) {
            return new ResponseEntity<>("Password must not be null", HttpStatus.BAD_REQUEST);
        }
        if (password.length() < 6 || password.length() > 8) {
            return new ResponseEntity<>("Password must be at least 6 characters and at most 8", HttpStatus.BAD_REQUEST);
        }
        Person response;
        try {
            response = service.updatePassword(password, cpf);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Could not update password", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response == null) {
            return new ResponseEntity<>("Could not found password from ID: " + cpf, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/forgotPassword/{cpf}")
    public ResponseEntity<?> forgotPassword(@PathVariable String cpf){
        log.info("Forgot password started >>>>");
        try{
            service.forgotPassword(cpf);
        }catch (Exception e){
            return new ResponseEntity<>("Could not retrieve password", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable String id) {
        try {
            service.inactivate(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Could not delete person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/reactivatePerson/{id}")
    public ResponseEntity<?> reactivatePerson(@PathVariable String id) {
        Person response;
        try {
            response = service.reactivatePerson(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Could not reactivate person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestHeader String userLogin, @RequestHeader String password) throws IllegalAccessException {
        log.info("Login started >>>>>");
        Person person;
        try {
            person = service.login(userLogin, password);
        } catch (IllegalAccessException e) {
            log.error("usuario ou senha incorretos");
            return new ResponseEntity<>("Usuario ou senha incorretos", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error("Error while validating userLogin and password");
            log.error(e.getMessage());
            return new ResponseEntity<>("Error while validating userLogin and password", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findbycpf/{cpf}")
    public ResponseEntity<?> findPersonByCPF(@PathVariable String cpf){
        log.info("find by cpf started >>>>>");
        Person person = null;
        try{
            log.info("validating cpf >>>>>");
            if(!ValidadorCpf.isCPF(cpf)) return new ResponseEntity<>("Please entry a valid CPF", HttpStatus.FORBIDDEN);
        }catch (Exception e){
            log.error("Error while validating CPF");
            return new ResponseEntity<>("Error while validating CPF", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            person = service.findPersonByCPF(cpf);
            if (person == null) {
                return new ResponseEntity<>("Could not find person with CPF: " + cpf,HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(person, HttpStatus.OK);
        }catch (Exception e){
            log.error("Error while trying to find person with CPF");
            return new ResponseEntity<>("Error while trying to find person with CPF", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
