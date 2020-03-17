//package com.mackenzie.cif.person.domain.dto;
//
//import com.mackenzie.cif.person.domain.domain.Address;
//import com.mackenzie.cif.person.domain.domain.Person;
//import com.mackenzie.cif.person.domain.domain.Therapist;
//import lombok.Data;
//import org.modelmapper.ModelMapper;
//
//@Data
//public class TherapistDTO {
//    private Integer id;
//
//    private Person person;
//    private Address address;
//    private String crefito;
//
//    private Boolean active;
//
//    public static TherapistDTO create(Therapist t){
//        ModelMapper modelMapper = new ModelMapper();
//        return modelMapper.map(t, TherapistDTO.class);
//    }
//
//
//}