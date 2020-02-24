package com.mackenzie.cif.person.application.conversor;

import com.mackenzie.cif.person.domain.domain.Patient;
import com.mackenzie.cif.person.domain.dto.PatientDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientConversor {
    public static Patient patientDtoToPatient(PatientDTO dto){
        log.info("Converting PatientDTO to Patient");
        Patient patient = new Patient();
        patient.setTherapistID(dto.getTherapistID());
        patient.setActive(dto.getActive());
        patient.setNote(dto.getNote());
        patient.setAdress(dto.getAdress());
        patient.setPerson(dto.getPerson());
        log.info("Successful conversion!");
        return patient;
    }

    public static PatientDTO patientToPatientDTO(Patient patient){
        log.info("Converting PatientDTO to Patient");
        PatientDTO dto = new PatientDTO();
        dto.setTherapistID(patient.getTherapistID());
        dto.setActive(patient.getActive());
        dto.setNote(patient.getNote());
        dto.setPerson(patient.getPerson());
        dto.setAdress(patient.getAdress());
        log.info("Successful conversion!");
        return dto;
    }
}
