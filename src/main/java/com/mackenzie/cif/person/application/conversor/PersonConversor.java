package com.mackenzie.cif.person.application.conversor;

import com.mackenzie.cif.person.domain.domain.Patient;
import com.mackenzie.cif.person.domain.domain.Person;
import com.mackenzie.cif.person.domain.dto.PersonRequest;

public class PersonConversor {

    public static Person personRequestToPerson(PersonRequest request){
        Person person = new Person();
        person.setPassword(request.getPassword());
        person.setActive(request.getActive());
        person.setTelephoneNumber(request.getTelephoneNumber());
        person.setSex(request.getSex());
        person.setEmail(request.getEmail());
        person.setLastName(request.getLastName());
        person.setFirstName(request.getFirstName());
        person.setAddress(request.getAddress());
        person.setBirthDate(request.getBirthDate());
        person.setId(request.getId());
        person.setCpf(request.getCpf());
        if(request.getPatient() != null){
            Patient patient = new Patient();
            patient.setNote(request.getPatient().getNote());
            person.setPatient(patient);
        }
        return person;
    }
}
