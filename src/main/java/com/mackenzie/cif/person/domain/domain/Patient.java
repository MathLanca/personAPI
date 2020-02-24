package com.mackenzie.cif.person.domain.domain;


import lombok.Data;


import javax.persistence.*;


@Entity
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientID;

    @Embedded
    private Person person;

    @Embedded
    private Adress adress;

    private Integer therapistID;
    private String note;

    private Boolean active;

    public void setAdress(Adress adress){
        this.adress = adress;
    }

    public void person(Person person){
        this.person = person;
    }
}
