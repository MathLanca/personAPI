package com.mackenzie.cif.person.domain.domain;


import lombok.Data;

@Data
public class Patient {
    private Person therapist;
    private String note;
}
