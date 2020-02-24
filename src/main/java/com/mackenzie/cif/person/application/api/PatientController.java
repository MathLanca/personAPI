package com.mackenzie.cif.person.application.api;

import com.mackenzie.cif.person.application.conversor.PatientConversor;
import com.mackenzie.cif.person.common.AES;
import com.mackenzie.cif.person.domain.domain.Patient;
import com.mackenzie.cif.person.domain.dto.PatientDTO;
import com.mackenzie.cif.person.domain.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/patient")
@Slf4j
public class PatientController {

    @Autowired
    private PatientService service;

    @Value("${secret.key}")
    private String KEY;

    @GetMapping("/listAll")
    public ResponseEntity listAllPatients(@RequestParam @Nullable Integer page, @RequestParam @Nullable Integer pageSize){
        log.info("List all questions >>>>>");
        List<PatientDTO> patients = null;
        try{
            if(page == null && pageSize != null){
                log.info("Received final index {}", pageSize);
                patients = service.listAllPatients(0,pageSize);
            }else if(page != null && pageSize != null){
                log.info("Received both index: "+ page + "and " +  pageSize);
                patients = service.listAllPatients(page,pageSize);
            }else {
                patients = service.listAllPatients();
            }
        }catch (Exception e){
            log.error("Error while listing all patients", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/findPatientById")
    public ResponseEntity findPatientById(@RequestParam Integer id){
        log.info("Find patient by id started >>>>>");

        PatientDTO patientDTO = null;
        try {
            patientDTO = service.findPatientById(id);
        }catch (Exception e){
            log.error("Error while finding patient with ID "+ id, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(patientDTO == null){
            String notFound = "There's no patient with ID "+id;
            return new ResponseEntity(notFound,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(patientDTO,HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity registerPatient(@RequestBody @Valid PatientDTO body){
        Patient patient = null;
        PatientDTO resposne;


        try {
            body.getPerson().setPassword(AES.encrypt(body.getPerson().getPassword(),KEY));
            patient = PatientConversor.patientDtoToPatient(body);
        }catch (Exception e){
            log.error("Could not convert PatientDTO to Patient");
            return new ResponseEntity("Could not convert PatientDTO to Patient", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            resposne = service.registerPatient(patient);
        }catch (Exception e){
            log.error("Could not register the patient");
            return new ResponseEntity("Could not register the patient", HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(resposne,HttpStatus.CREATED);
    }

    @PutMapping("/updateRegister/{id}")
    public ResponseEntity updateRegister(@PathVariable Integer id,@RequestBody @Valid PatientDTO body){
        body.setPatientID(id);
        PatientDTO response = null;
        try {
            response = service.updateRegister(PatientConversor.patientDtoToPatient(body), id);
        }catch (Exception e){
            return new ResponseEntity("Could not update register",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(response == null){
            return new ResponseEntity("Could not found patient with ID: "+id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
