//package com.mackenzie.cif.person.domain.service;
//
//import com.mackenzie.cif.person.common.AES;
//import com.mackenzie.cif.person.domain.domain.Patient;
//import com.mackenzie.cif.person.domain.domain.Therapist;
//import com.mackenzie.cif.person.domain.dto.PatientDTO;
//import com.mackenzie.cif.person.domain.dto.TherapistDTO;
//import com.mackenzie.cif.person.domain.repository.TherapistRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.coyote.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//public class TherapistService {
//    @Value("${secret.key}")
//    private String KEY;
//
//    @Autowired
//    private TherapistRepository rep;
//
//    public List<TherapistDTO> listAllTherapists(){
//        log.info("Service list all therapists");
//        return rep.findAll().stream().map(TherapistDTO::create).collect(Collectors.toList());
//    }
//
//    public TherapistDTO findTherapistById(String id){
//        log.info("Service find therapist by id >>>>>");
//        Optional<Therapist> therapist = null;
//        TherapistDTO therapistDTO = null;
//        try{
//            therapist = rep.findById(id);
//            therapistDTO = TherapistDTO.create(therapist.get());
//            if(!therapistDTO.getActive()){
//                return null;
//            }
//        }catch (Exception e){
//            log.error("Could not get therapist with id {}", id);
//            log.error(e.getMessage());
//            log.error(String.valueOf(e.getCause()));
//            return null;
//        }
//        return therapistDTO;
//    }
//
//    public TherapistDTO findTherapistByCrefito(String crefito){
//        log.info("Service find therapist by crefito >>>>>");
//        Optional<Therapist> therapist = null;
//        TherapistDTO therapistDTO = null;
//        try{
//            therapist = rep.findByCrefito(crefito);
//            therapistDTO = TherapistDTO.create(therapist.get());
//            if(!therapistDTO.getActive()){
//                return null;
//            }
//        }catch (Exception e){
//            log.error("Could not get therapist with crefito {}", crefito);
//            log.error(e.getMessage());
//            log.error(String.valueOf(e.getCause()));
//            return null;
//        }
//        return therapistDTO;
//    }
//
//    public TherapistDTO registerTherapist(Therapist therapist){
//        therapist.setActive(true);
//        therapist.getPerson().setPassword(AES.encrypt(therapist.getPerson().getPassword(),KEY));
//        return TherapistDTO.create(rep.save(therapist));
//    }
//
//    public TherapistDTO updateTherapist(Therapist therapist, String id){
//        Optional<Therapist> optional = rep.findById(id);
//        if(optional.isPresent()){
//            Therapist db = optional.get();
//            if(!db.getPerson().getPassword().equals(therapist.getPerson().getPassword()))
//                therapist.getPerson().setPassword(AES.encrypt(therapist.getPerson().getPassword(), KEY));
//            db.setAddress(therapist.getAddress());
//            db.setCrefito(therapist.getCrefito());
//            db.setPerson(therapist.getPerson());
//            rep.save(db);
//            return TherapistDTO.create(db);
//        } else {
//            throw new RuntimeException("Could not update registry");
//        }
//    }
//
//    public TherapistDTO updatePassword(String password, String id){
//        Optional<Therapist> optional = rep.findById(id);
//        if(optional.isPresent()){
//            Therapist db = optional.get();
//            db.getPerson().setPassword(AES.encrypt(password,KEY));
//            rep.save(db);
//            return TherapistDTO.create(db);
//        } else{
//            throw new RuntimeException("Could not update password");
//        }
//
//    }
//
//    public void delete(String id) {
//        Optional<Therapist> optional = rep.findById(id);
//        if(optional.isPresent()){
//            Therapist db = optional.get();
//            db.setActive(false);
//            rep.save(db);
//        } else {
//            throw new RuntimeException("Could not delete therapist ");
//        }
//    }
//
//    public TherapistDTO reactivateTherapist(String id){
//        Optional<Therapist> optional = rep.findById(id);
//        if(optional.isPresent()){
//            Therapist db = optional.get();
//            db.setActive(true);
//            rep.save(db);
//            return TherapistDTO.create(db);
//        } else {
//            throw new RuntimeException("Could not reactivate therapist with id ");
//        }
//    }
//}