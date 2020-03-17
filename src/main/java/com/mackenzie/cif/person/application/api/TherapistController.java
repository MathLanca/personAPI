//package com.mackenzie.cif.person.application.api;
//
//import com.mackenzie.cif.person.application.conversor.PatientConversor;
//import com.mackenzie.cif.person.domain.domain.Patient;
//import com.mackenzie.cif.person.domain.domain.Therapist;
//import com.mackenzie.cif.person.domain.dto.PatientDTO;
//import com.mackenzie.cif.person.domain.service.TherapistService;
//import com.mackenzie.cif.person.domain.dto.TherapistDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/v1/therapist")
//@Slf4j
//public class TherapistController {
//
//    @Autowired
//    private TherapistService service;
//
//    @GetMapping("/listAll")
//    public ResponseEntity listAll() {
//        log.info("List all Therapist >>>>> ");
//        //List<TherapistDTO> therapists = new ArrayList<>();
//        List<TherapistDTO> therapists = null;
//        try {
//            therapists = service.listAllTherapists();
//        } catch (Exception e) {
//            log.error("Error while listing all therapist", e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        return new ResponseEntity<>(therapists, HttpStatus.OK);
//    }
//
//    @GetMapping("/findTherapistById")
//    public ResponseEntity findTherapistById(@RequestParam String id) {
//        log.info("Find therapist by id started >>>>>");
//        TherapistDTO therapistDTO = null;
//        try {
//            therapistDTO = service.findTherapistById(id);
//        } catch (Exception e) {
//            log.error("Error while finding therapist with ID " + id, e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        if (therapistDTO == null) {
//            String notFound = "There's no therapist with ID " + id;
//            return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(therapistDTO,HttpStatus.OK);
//    }
//
//    @GetMapping("/findTherapistByCrefito")
//    public ResponseEntity findTherapistByCrefito(@RequestHeader String crefito) {
//        log.info("Find therapist by crefito started >>>>>");
//        TherapistDTO therapistDTO = null;
//        try {
//            String crefito_field = crefito.replace("/","").replace("-","").replace(" ","");
//            crefito_field = crefito_field.trim();
//            therapistDTO = service.findTherapistByCrefito(crefito_field);
//        } catch (Exception e) {
//            log.error("Error while finding therapist with crefito " + crefito, e.getMessage());
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        if (therapistDTO == null) {
//            String notFound = "There's no therapist with crefito " + crefito;
//            return new ResponseEntity(notFound, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity(therapistDTO,HttpStatus.OK);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity registerPatient(@RequestBody @Valid TherapistDTO body){
//        log.info("Register therapist started >>>>>");
//        Therapist therapist = null;
//        TherapistDTO resposne;
//        try {
//            therapist = Therapist.create(body);
//        }catch (Exception e){
//            log.error("Could not convert TherapistDTO to Therapist");
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not convert TherapistDTO to Patient", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        try{
//            resposne = service.registerTherapist(therapist);
//        }catch (Exception e){
//            log.error("Could not register the therapist");
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not register the patient", HttpStatus.NOT_MODIFIED);
//        }
//        return new ResponseEntity(resposne,HttpStatus.CREATED);
//    }
//
//    @PutMapping("/updateTherapist/{id}")
//    public ResponseEntity updateTherapist(@PathVariable String id, @RequestBody @Valid TherapistDTO body){
//        log.info("Update therapist started >>>>>>>>");
//        body.setId(id);
//        TherapistDTO response = null;
//        try{
//            response = service.updateTherapist(Therapist.create(body), id);
//        } catch(Exception e){
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not update therapist", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//       if(response == null){
//           return new ResponseEntity("Could not find therapist with id " + id, HttpStatus.NOT_FOUND);
//       }
//       return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    @PutMapping("/updatePassword/{id}")
//    public ResponseEntity updatePassword(@RequestParam String password, @PathVariable String id){
//        log.info("Update password started >>>>> ");
//        if(password == null || password == ""){
//            return new ResponseEntity("Password must be not null", HttpStatus.BAD_REQUEST);
//        }
//        if(password.length() < 6){
//            return new ResponseEntity("Password length must be at least 6 characters and at most 8", HttpStatus.BAD_REQUEST);
//        }
//        TherapistDTO response = null;
//        try{
//            response = service.updatePassword(password, id);
//        } catch(Exception e){
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not update password", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        if(response == null){
//            return new ResponseEntity("Could not find therapist with id"+id, HttpStatus.NOT_FOUND);
//        }
//            return new ResponseEntity(response, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/deleteTherapist/{id}")
//    public ResponseEntity deleteTherapist(@PathVariable String id){
//        log.info("Delete therapist started >>>>> ");
//        try{
//            service.delete(id);
//        } catch(Exception e){
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not delete therapist", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity("Therapist deleted", HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping("/reactivateTherapist/{id}")
//    public ResponseEntity reactivatetherapist(@PathVariable String id){
//        log.info("Reactivate therapist started >>>>> ");
//        TherapistDTO response = null;
//        try{
//            response = service.reactivateTherapist(id);
//        } catch(Exception e){
//            log.error(e.getMessage());
//            return new ResponseEntity("Could not reactivate therapist", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity("Therapist reactivated", HttpStatus.OK);
//    }
//}
