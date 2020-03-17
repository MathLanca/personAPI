package com.mackenzie.cif.person.domain.dto;

import com.mackenzie.cif.person.domain.domain.Address;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Document(collection = "Person")
public class PersonRequest {

    @Id
    private String id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDateTime birthDate;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 8)
    private String password;

    private String sex;

    private String telephoneNumber;

    private Address address;

    private String cpf;

    private PatientRequest patient;

    private Boolean active;

    public void setAddress(Address address){
        this.address = address;
    }
}
