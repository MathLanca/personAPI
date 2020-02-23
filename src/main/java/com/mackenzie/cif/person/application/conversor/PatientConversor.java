package com.mackenzie.cif.person.application.conversor;

import com.mackenzie.cif.person.domain.domain.Adress;
import com.mackenzie.cif.person.domain.domain.Patient;
import com.mackenzie.cif.person.domain.domain.Person;
import com.mackenzie.cif.person.domain.dto.PatientDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PatientConversor {
    public static Patient patientDtoToPatient(PatientDTO dto){
        log.info("Converting PatientDTO to Patient");
        Patient patient = new Patient();
        patient.setTherapistID(dto.getTherapistID());
        patient.setNote(dto.getNote());

        Person person = new Person();
            person.setFirstName(dto.getPerson().getFirstName());
            person.setLastName(dto.getPerson().getLastName());
            person.setEmail(dto.getPerson().getEmail());
            person.setPassword(dto.getPerson().getPassword());
            person.setSex(dto.getPerson().getSex());
            person.setTelephoneNumber(dto.getPerson().getTelephoneNumber());
        patient.setPerson(person);

        Adress adress = new Adress();
            adress.setCity(dto.getAdress().getCity());
            adress.setHouseNumber(dto.getAdress().getHouseNumber());
            adress.setNeighborhood(dto.getAdress().getNeighborhood());
            adress.setPostalCode(dto.getAdress().getPostalCode());
            adress.setPublicPlace(dto.getAdress().getPublicPlace());
            adress.setState(dto.getAdress().getState());
        patient.setAdress(adress);
        log.info("Successful conversion!");
        return patient;
    }

    public static PatientDTO patientToPatientDTO(Patient patient){
        log.info("Converting PatientDTO to Patient");
        PatientDTO dto = new PatientDTO();
        dto.setTherapistID(patient.getTherapistID());
        dto.setNote(patient.getNote());

        Person person = new Person();
        person.setFirstName(patient.getPerson().getFirstName());
        person.setLastName(patient.getPerson().getLastName());
        person.setEmail(patient.getPerson().getEmail());
        person.setPassword(patient.getPerson().getPassword());
        person.setSex(patient.getPerson().getSex());
        person.setTelephoneNumber(patient.getPerson().getTelephoneNumber());
        dto.setPerson(person);

        Adress adress = new Adress();
        adress.setCity(patient.getAdress().getCity());
        adress.setHouseNumber(patient.getAdress().getHouseNumber());
        adress.setNeighborhood(patient.getAdress().getNeighborhood());
        adress.setPostalCode(patient.getAdress().getPostalCode());
        adress.setPublicPlace(patient.getAdress().getPublicPlace());
        adress.setState(patient.getAdress().getState());
        dto.setAdress(adress);
        log.info("Successful conversion!");
        return dto;
    }
}
