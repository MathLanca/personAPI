//package com.mackenzie.cif.person.domain.service;
//
//import com.mackenzie.cif.person.common.AES;
//import com.mackenzie.cif.person.domain.domain.Patient;
//import com.mackenzie.cif.person.domain.dto.PatientDTO;
//import com.mackenzie.cif.person.domain.repository.PatientRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//public class PatientService {
//
//    @Value("${secret.key}")
//    private String KEY;
//
//    @Autowired
//    private PatientRepository repository;
//
//    public List<PatientDTO> listAllPatients(){
//        log.info("Service list all patients >>>>>");
//        List<PatientDTO> patients = repository.findAllByActive(true).stream().map(PatientDTO::create).collect(Collectors.toList());
//        return patients;
//    }
//    public List<PatientDTO> listAllPatients(Integer page, Integer size){
//        log.info("Service list all patients >>>>>");
//        Pageable pageable = PageRequest.of(page, size);
//        List<PatientDTO> patients = repository.findAll(pageable).stream().map(PatientDTO::create).collect(Collectors.toList());
//        patients.removeIf(patientDTO -> !patientDTO.getActive());
//        return patients;
//    }
//
//    public PatientDTO findPatientById(String id){
//        log.info("Service find patient by id >>>>>");
//        Optional<Patient> patient = null;
//        PatientDTO patientDTO = null;
//        try{
//            patient = repository.findById(id);
//            patientDTO = PatientDTO.create(patient.get());
//            if(!patientDTO.getActive()){
//                return null;
//            }
//        }catch (Exception e){
//            log.error("Could not get patient with id {}", id);
//            log.error(e.getMessage());
//            log.error(String.valueOf(e.getCause()));
//            return null;
//        }
//        return patientDTO;
//    }
//
//    public PatientDTO registerPatient(Patient patient){
//        patient.setActive(true);
//        patient.getPerson().setPassword(AES.encrypt(patient.getPerson().getPassword(),KEY));
//        Patient db = null;
//        try{
//           db = repository.save(patient);
//        }catch (DuplicateKeyException e){
//            log.error("Could not register patient, email already registered");
//            log.error(e.getMessage());
//            throw e;
//        }
//        return PatientDTO.create(db);
//    }
//
//    public PatientDTO updatePatient(Patient patient, String id){
//        Optional<Patient> optional = repository.findById(id);
//
//        if(optional.isPresent()){
//            Patient db = optional.get();
//            if(!db.getPerson().getPassword().equals(patient.getPerson().getPassword()))
//                patient.getPerson().setPassword(AES.encrypt(patient.getPerson().getPassword(),KEY));
//            db.setTherapist_id(patient.getTherapist_id());
//            db.setNote(patient.getNote());
//            db.setAddress(patient.getAddress());
//            db.setPerson(patient.getPerson());
//            repository.save(db);
//            return PatientDTO.create(db);
//        }else{
//            throw new RuntimeException("Could not update registry");
//        }
//    }
//
//    public PatientDTO updatePassword(String password, String id){
//        Optional<Patient> optional = repository.findById(id);
//
//        if(optional.isPresent()){
//            Patient db = optional.get();
//            db.getPerson().setPassword(AES.encrypt(password,KEY));
//            repository.save(db);
//            return PatientDTO.create(db);
//        }else{
//            throw new RuntimeException("Could not update password");
//        }
//    }
//
//    public void delete(String id){
//        Optional<Patient> optional = repository.findById(id);
//
//        if(optional.isPresent()){
//            Patient db = optional.get();
//            db.setActive(false);
//            repository.save(db);
//        }else{
//            throw new RuntimeException("Could not delete patient");
//        }
//    }
//
//    public PatientDTO reactivatePatient(String id){
//        Optional<Patient> optional = repository.findById(id);
//        if(optional.isPresent()){
//            Patient db = optional.get();
//            db.setActive(true);
//            repository.save(db);
//            return  PatientDTO.create(db);
//        }else{
//            throw new RuntimeException("Could not activate patient");
//        }
//    }
//
//    public PatientDTO login(String email, String password) throws IllegalAccessException {
//        log.info("Login service >>>>>");
//        Boolean exist = null;
//
//        log.info("encrypting password >>>>>");
//        password = AES.encrypt(password, KEY);
//
//        try{
//            log.info("validating existence of patient");
//             exist = repository.existsByPersonEmailAndPersonPassword(email,password);
//        }catch (Exception e){
//            log.error("Error while validating email and password");
//            log.error(e.getMessage());
//            throw e;
//        }
//
//        PatientDTO patientDTO = null;
//        if(exist){
//            log.info("searching patient on database");
//            Patient p = repository.findByPersonEmail(email);
//            patientDTO = PatientDTO.create(p);
//        }else{
//            log.error("usuario ou senha incorretos");
//            throw new IllegalAccessException("Email ou senha incorretos");
//        }
//        return patientDTO;
//    }
//
//}
