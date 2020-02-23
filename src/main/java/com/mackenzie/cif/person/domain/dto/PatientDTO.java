package com.mackenzie.cif.person.domain.dto;

import com.mackenzie.cif.person.domain.domain.Adress;
import com.mackenzie.cif.person.domain.domain.Patient;
import com.mackenzie.cif.person.domain.domain.Person;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class PatientDTO {
    private Integer patientID;

    @NotNull
    private Integer therapistID;

    @NotNull
    @Valid
    private Person person;

    @NotNull
    @Valid
    private Adress adress;

    private String note;

    public static PatientDTO create(Patient p){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, PatientDTO.class);
    }
}
