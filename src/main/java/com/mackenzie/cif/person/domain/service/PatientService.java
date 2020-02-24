package com.mackenzie.cif.person.domain.service;

import com.mackenzie.cif.person.common.AES;
import com.mackenzie.cif.person.domain.domain.Patient;
import com.mackenzie.cif.person.domain.dto.PatientDTO;
import com.mackenzie.cif.person.domain.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PatientService {

    @Value("${secret.key}")
    private String KEY;

    @Autowired
    private PatientRepository repository;

    public List<PatientDTO> listAllPatients(){
        log.info("Service list all patients >>>>>");
        List<PatientDTO> patients = repository.findAllByActive(true).stream().map(PatientDTO::create).collect(Collectors.toList());
        return patients;
    }
    public List<PatientDTO> listAllPatients(Integer initialIndex, Integer finalIndex){
        log.info("Service list all patients >>>>>");
        Pageable firstPageWithTwoElements = PageRequest.of(initialIndex, finalIndex);
        List<PatientDTO> patients = repository.findAll(firstPageWithTwoElements).stream().map(PatientDTO::create).collect(Collectors.toList());
        patients.removeIf(patientDTO -> patientDTO.getActive());
        return patients;
    }

    public PatientDTO findPatientById(Integer id){
        log.info("Service find patient by id >>>>>");
        Optional<Patient> patient = null;
        PatientDTO patientDTO = null;
        try{
            patient = repository.findById(id);
            patientDTO = PatientDTO.create(patient.get());
            if(!patientDTO.getActive()){
                return null;
            }
        }catch (Exception e){
            log.error("Could not get patient with id {}", id);
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            return null;
        }
        return patientDTO;
    }

    public PatientDTO registerPatient(Patient patient){
        patient.getPerson().setPassword(AES.encrypt(patient.getPerson().getPassword(),KEY));
        return PatientDTO.create(repository.save(patient));
    }

    public PatientDTO updatePatient(Patient patient, Integer id){
        Optional<Patient> optional = repository.findById(id);

        if(optional.isPresent()){
            Patient db = optional.get();
            if(!db.getPerson().getPassword().equals(patient.getPerson().getPassword()))
                patient.getPerson().setPassword(AES.encrypt(patient.getPerson().getPassword(),KEY));
            db.setTherapistID(patient.getTherapistID());
            db.setNote(patient.getNote());
            db.setAdress(patient.getAdress());
            db.setPerson(patient.getPerson());
            repository.save(db);
            return PatientDTO.create(db);
        }else{
            throw new RuntimeException("Could not update registry");
        }
    }

    public void delete(Integer id){
        Optional<Patient> optional = repository.findById(id);

        if(optional.isPresent()){
            Patient db = optional.get();
            db.setActive(false);
            repository.save(db);
        }else{
            throw new RuntimeException("Could not delete patient");
        }
    }

}
