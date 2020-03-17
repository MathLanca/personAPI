//package com.mackenzie.cif.person.application.api;
//
//import com.mackenzie.cif.person.application.conversor.PatientConversor;
//import com.mackenzie.cif.person.domain.domain.Patient;
//import com.mackenzie.cif.person.domain.dto.PatientDTO;
//import com.mackenzie.cif.person.domain.service.PatientService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.lang.Nullable;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/v1/patient")
//@Slf4j
//public class PatientController {
//
//    @Autowired
//    private PatientService service;
//
//    @Value("${secret.key}")
//    private String KEY;
//
//    @CrossOrigin(origins = "*")
//    @GetMapping("/listAll")
//    public ResponseEntity listAllPatients(@RequestParam @Nullable Integer page, @RequestParam @Nullable Integer size) {
//        log.info("List all questions >>>>>");
//        List<PatientDTO> patients = null;
//        try {
//            if (page == null && size != null) {
//                log.info("Received final index {}", size);
//                patients = service.listAllPatients(0, size);
//            } else if (page != null && size != null) {
//                log.info("Received both index: " + page + "and " + size);
//                patients = service.listAllPatients(page, size);
//            } else {
//                patients = service.listAllPatients();
//            }
//        } catch (Exception e) {
//            log.error("Error while listing all patients", e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>(patients, HttpStatus.OK);
//    }
//
//    @CrossOrigin(origins = "*")
//    @GetMapping("/findPatientById")
//    public ResponseEntity findPatientById(@RequestParam String id) {
//        log.info("Find patient by id started >>>>>");
//
//        PatientDTO patientDTO = null;
//        try {
//            patientDTO = service.findPatientById(id);
//        } catch (Exception e) {
//            log.error("Error while finding patient with ID " + id, e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        if (patientDTO == null) {
//            String notFound = "There's no patient with ID " + id;
//            return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(patientDTO, HttpStatus.OK);
//    }
//
//    @CrossOrigin(origins = "*")
//    @PostMapping("/register")
//    public ResponseEntity registerPatient(@RequestBody @Valid PatientDTO body) {
//        log.info("Register patient started >>>>>");
//        Patient patient = null;
//        PatientDTO resposne;
//        try {
//            patient = PatientConversor.patientDtoToPatient(body);
//        } catch (Exception e) {
//            log.error("Could not convert PatientDTO to Patient");
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not convert PatientDTO to Patient", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        try {
//            resposne = service.registerPatient(patient);
//        }catch (DuplicateKeyException e){
//            log.error("Could not register patient, email already registered");
//            log.error(e.getMessage());
//            return new ResponseEntity("Email already registered", HttpStatus.FORBIDDEN);
//        } catch (Exception e) {
//            log.error("Could not register the patient");
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not register the patient", HttpStatus.NOT_MODIFIED);
//        }
//        return new ResponseEntity(resposne, HttpStatus.CREATED);
//    }
//
//    @CrossOrigin(origins = "*")
//    @PutMapping("/updatePatient/{id}")
//    public ResponseEntity updatePatient(@PathVariable String id, @RequestBody @Valid PatientDTO body) {
//        log.info("Update patient started >>>>>");
//        body.setId(id);
//        PatientDTO response = null;
//        try {
//            response = service.updatePatient(PatientConversor.patientDtoToPatient(body), id);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not update patient", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        if (response == null) {
//            return new ResponseEntity("Could not found patient with ID: " + id, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    @CrossOrigin(origins = "*")
//    @PutMapping("/updatePassword/{id}")
//    public ResponseEntity updatePassword(@RequestParam String password, @PathVariable String id) {
//        log.info("Update password started >>>>>");
//        if (password == null || password.equals("")) {
//            return new ResponseEntity("Password must not be null", HttpStatus.BAD_REQUEST);
//        }
//        if (password.length() < 6 || password.length() > 8) {
//            return new ResponseEntity("Password must be at least 6 characters and at most 8", HttpStatus.BAD_REQUEST);
//        }
//        PatientDTO response = null;
//        try {
//            response = service.updatePassword(password, id);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not update password", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        if (response == null) {
//            return new ResponseEntity("Could not found password from ID: " + id, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    @CrossOrigin(origins = "*")
//    @DeleteMapping("/deletePatient/{id}")
//    public ResponseEntity deletePatient(@PathVariable String id) {
//        try {
//            service.delete(id);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not delete patient", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//    @CrossOrigin(origins = "*")
//    @PutMapping("/reactivatePatient/{id}")
//    public ResponseEntity reactivatePatient(@PathVariable String id) {
//        PatientDTO response = null;
//        try {
//            response = service.reactivatePatient(id);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not reactivate patient", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    @CrossOrigin(origins = "*")
//    @GetMapping("/login")
//    public ResponseEntity login(@RequestHeader String email, @RequestHeader String password) throws IllegalAccessException {
//        log.info("Login started >>>>>");
//        PatientDTO patientDTO = null;
//        try {
//            patientDTO = service.login(email, password);
//        } catch (IllegalAccessException e) {
//            log.error("usuario ou senha incorretos");
//            return new ResponseEntity("Usuario ou senha incorretos", HttpStatus.UNAUTHORIZED);
//        } catch (Exception e) {
//            log.error("Error while validating email and password");
//            log.error(e.getMessage());
//            return new ResponseEntity("Error while validating email and password", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity(patientDTO, HttpStatus.OK);
//    }
//}
