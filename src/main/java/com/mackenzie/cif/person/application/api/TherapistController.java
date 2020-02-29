package com.mackenzie.cif.person.application.api;

import com.mackenzie.cif.person.application.conversor.PatientConversor;
import com.mackenzie.cif.person.domain.domain.Patient;
import com.mackenzie.cif.person.domain.domain.Therapist;
import com.mackenzie.cif.person.domain.dto.PatientDTO;
import com.mackenzie.cif.person.domain.service.TherapistService;
import com.mackenzie.cif.person.domain.dto.TherapistDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/therapist")
@Slf4j
public class TherapistController {

    @Autowired
    private TherapistService service;

    @GetMapping("/listAll")
    public ResponseEntity listAll() {
        log.info("List all Therapist >>>>> ");
        //List<TherapistDTO> therapists = new ArrayList<>();
        List<TherapistDTO> therapists = null;
        try {
            therapists = service.listAllTherapists();
        } catch (Exception e) {
            log.error("Error while listing all therapist", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(therapists, HttpStatus.OK);
    }

    @GetMapping("/findTherapistById")
    public ResponseEntity findTherapistById(@RequestParam Integer id) {
        log.info("Find therapist by id started >>>>>");
        TherapistDTO therapistDTO = null;
        try {
            therapistDTO = service.findTherapistById(id);
        } catch (Exception e) {
            log.error("Error while finding therapist with ID " + id, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (therapistDTO == null) {
            String notFound = "There's no therapist with ID " + id;
            return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(therapistDTO,HttpStatus.OK);
    }

    @GetMapping("/findTherapistByCrefito")
    public ResponseEntity findTherapistByCrefito(@RequestHeader String crefito) {
        log.info("Find therapist by crefito started >>>>>");
        TherapistDTO therapistDTO = null;
        try {
            String crefito_field = crefito.replace("/","").replace("-","").replace(" ","");
            crefito_field = crefito_field.trim();
            therapistDTO = service.findTherapistByCrefito(crefito_field);
        } catch (Exception e) {
            log.error("Error while finding therapist with crefito " + crefito, e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (therapistDTO == null) {
            String notFound = "There's no therapist with crefito " + crefito;
            return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(therapistDTO,HttpStatus.OK);
    }


}
