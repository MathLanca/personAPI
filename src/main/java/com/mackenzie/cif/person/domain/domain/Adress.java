package com.mackenzie.cif.person.domain.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
public class Adress {
    @NotNull
    private String publicPlace;

    @NotNull
    private Integer houseNumber;

    @NotNull
    private String neighborhood;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String postalCode;
}
