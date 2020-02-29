package com.mackenzie.cif.person.domain.domain;

import com.mackenzie.cif.person.domain.dto.TherapistDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@Data
public class Therapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer therapistID;

    @Embedded
    private Person person;

    @Embedded
    private Adress adress;

    private String crefito;
    private Boolean active;

    public void setAdress(Adress adress){
        this.adress = adress;
    }

    public void person(Person person){
        this.person = person;
    }

    public static Therapist create(TherapistDTO t){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(t, Therapist.class);
    }
}
