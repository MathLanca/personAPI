package com.mackenzie.cif.person.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String sex;
    private String telephoneNumber;
}
