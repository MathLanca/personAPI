package com.mackenzie.cif.person.application.api;

import com.mackenzie.cif.person.application.conversor.PersonConversor;
import com.mackenzie.cif.person.common.ValidadorCpf;
import com.mackenzie.cif.person.domain.domain.Person;
import com.mackenzie.cif.person.domain.dto.PersonRequest;
import com.mackenzie.cif.person.domain.service.PersonService;
import com.mongodb.DuplicateKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/person")
@Slf4j
public class PersonController {

    @Autowired
    private PersonService service;

    @CrossOrigin("*")
    @PostMapping("/register")
    public ResponseEntity<Void> registerPerson(@RequestBody PersonRequest body){
        log.info("Register person started >>>>>");
        Person therapist = null;
        Person person = null;

        if(body.getCpf() == null || !ValidadorCpf.isCPF(body.getCpf())){
            log.error("Invalid cpf!");
            return new ResponseEntity("Please enter a valid CPF", HttpStatus.FORBIDDEN);
        }

        person = PersonConversor.personRequestToPerson(body);
        if(body.getPatient() != null && body.getPatient().getTherapistID() != null){
            therapist = service.findPersonById(body.getPatient().getTherapistID());
            if(therapist != null){
                person.getPatient().setTherapist(therapist);
            }else{
                log.error("Could not find Therapist for");
                return new ResponseEntity("Could not register the patient", HttpStatus.NOT_MODIFIED);
            }
        }
        try {
            service.registerPerson(person);
        }catch (DuplicateKeyException e){
            log.error("Could not register patient, email already registered");
            log.error(e.getMessage());
            return new ResponseEntity("Email already registered", HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            log.error("Could not register the patient");
            log.error(e.getMessage());
            return new ResponseEntity("Could not register the patient", HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @CrossOrigin("*")
    @GetMapping("/listAllPatient")
    public ResponseEntity listAllPatient(){
        List<Person> patients = service.listAllPatient();

        return new ResponseEntity(patients, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/listAllTherapist")
    public ResponseEntity listAllTherapist(){
        List<Person> patients = service.listAllTherapist();

        return new ResponseEntity(patients, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/findById/{id}")
    public ResponseEntity findPersonById(@PathVariable String id) {
        log.info("Find patient by id started >>>>>");

        Person person = null;
        try {
            person = service.findPersonById(id);
        } catch (Exception e) {
            log.error("Error while finding person with ID " + id, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (person == null) {
            String notFound = "Could not find person";
            return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(person, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/updatePerson/{id}")
    public ResponseEntity updatePerson(@PathVariable String id, @RequestBody @Valid Person body) {
        log.info("Update patient started >>>>>");
        if(body.getCpf() == null || !ValidadorCpf.isCPF(body.getCpf())){
            log.error("Invalid cpf!");
            return new ResponseEntity("Please enter a valid CPF", HttpStatus.FORBIDDEN);
        }
        body.setId(id);
        Person response = null;
        try {
            response = service.updatePerson(body, id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity("Could not update person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response == null) {
            return new ResponseEntity("Could not found person", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
