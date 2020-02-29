package com.mackenzie.cif.person.domain.service;

import com.mackenzie.cif.person.domain.domain.Therapist;
import com.mackenzie.cif.person.domain.dto.PatientDTO;
import com.mackenzie.cif.person.domain.dto.TherapistDTO;
import com.mackenzie.cif.person.domain.repository.TherapistRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TherapistService {
    @Autowired
    private TherapistRepository rep;

    public List<TherapistDTO> listAllTherapists(){
        log.info("Service list all therapists");
        return rep.findAll().stream().map(TherapistDTO::create).collect(Collectors.toList());
    }

    public TherapistDTO findTherapistById(Integer id){
        log.info("Service find therapist by id >>>>>");
        Optional<Therapist> therapist = null;
        TherapistDTO therapistDTO = null;
        try{
            therapist = rep.findById(id);
            therapistDTO = TherapistDTO.create(therapist.get());
            if(!therapistDTO.getActive()){
                return null;
            }
        }catch (Exception e){
            log.error("Could not get therapist with id {}", id);
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            return null;
        }
        return therapistDTO;
    }

    public TherapistDTO findTherapistByCrefito(String crefito){
        log.info("Service find therapist by crefito >>>>>");
        Optional<Therapist> therapist = null;
        TherapistDTO therapistDTO = null;
        try{
            therapist = rep.findByCrefito(crefito);
            therapistDTO = TherapistDTO.create(therapist.get());
            if(!therapistDTO.getActive()){
                return null;
            }
        }catch (Exception e){
            log.error("Could not get therapist with crefito {}", crefito);
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            return null;
        }
        return therapistDTO;
    }

}
