package com.mackenzie.cif.person.domain.service;

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
        List<PatientDTO> patients = repository.findAll().stream().map(PatientDTO::create).collect(Collectors.toList());
        return patients;
    }
    public List<PatientDTO> listAllPatients(Integer initialIndex, Integer finalIndex){
        log.info("Service list all patients >>>>>");
        Pageable firstPageWithTwoElements = PageRequest.of(initialIndex, finalIndex);
        List<PatientDTO> patients = repository.findAll(firstPageWithTwoElements).stream().map(PatientDTO::create).collect(Collectors.toList());
        return patients;
    }


    public PatientDTO findPatientById(Integer id){
        log.info("Service find patient by id >>>>>");
        Optional<Patient> patient = null;
        PatientDTO patientDTO = null;
        try{
            patient = repository.findById(id);
            patientDTO = PatientDTO.create(patient.get());
        }catch (Exception e){
            log.error("Could not get patient with id {}", id);
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            return null;
        }
        return patientDTO;
    }

    public PatientDTO registerPatient(Patient patient){
        return PatientDTO.create(repository.save(patient));
    }

}
