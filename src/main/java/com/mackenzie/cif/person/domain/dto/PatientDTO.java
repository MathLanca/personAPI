//package com.mackenzie.cif.person.domain.dto;
//
//import com.mackenzie.cif.person.domain.domain.Address;
//import com.mackenzie.cif.person.domain.domain.Patient;
//import com.mackenzie.cif.person.domain.domain.Person;
//import lombok.Data;
//import org.modelmapper.ModelMapper;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//
//@Data
//public class PatientDTO {
//    private String id;
//
//    @NotNull
//    private String therapist_id;
//
//    @NotNull
//    @Valid
//    private Person person;
//
//    @NotNull
//    @Valid
//    private Address address;
//
//    private String note;
//
//    private Boolean active;
//
//    public static PatientDTO create(Patient p){
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(p, PatientDTO.class);
//    }
//}
