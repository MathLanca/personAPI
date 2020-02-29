package com.mackenzie.cif.person.domain.dto;

import com.mackenzie.cif.person.domain.domain.Adress;
import com.mackenzie.cif.person.domain.domain.Person;
import com.mackenzie.cif.person.domain.domain.Therapist;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class TherapistDTO {
    private Integer therapistID;

    private Person person;
    private Adress adress;
    private String crefito;

    private Boolean active;

    public static TherapistDTO create(Therapist t){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(t, TherapistDTO.class);
    }


}
