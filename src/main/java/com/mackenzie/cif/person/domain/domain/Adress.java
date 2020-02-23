package com.mackenzie.cif.person.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Adress {
    private String publicPlace;
    private Integer houseNumber;
    private String neighborhood;
    private String city;
    private String state;
    private String postalCode;
}
