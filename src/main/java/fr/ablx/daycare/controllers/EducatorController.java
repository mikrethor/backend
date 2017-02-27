package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.Educator;
import fr.ablx.daycare.jpa.EducatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EducatorController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    EducatorRepository educatorRepo;

    @RequestMapping("/daycares/{id}/educators")
    public List<Educator> getEducators(@PathVariable Long id) {
        List<Educator> educators = new ArrayList<>();

        educatorRepo.findAll().forEach(entity -> educators.add(entity));

        return educators;
    }

    @RequestMapping("/daycares/{idDaycare}/educators/{idEducator}")
    public Educator getEducator(@PathVariable Long idDaycare, @PathVariable Long idEducator) {

        return educatorRepo.findEducatorByIdAndDaycare(idDaycare, idEducator);
    }

    @DeleteMapping("/daycares/{idDaycare}/educators/{idEducator}")
    public Boolean deleteEducator(@PathVariable Long idDaycare, @PathVariable Long idEducator) throws DayCareException {
        try {
            educatorRepo.delete(idEducator);
        } catch (Exception e) {
            throw new DayCareException("Error when deleting educator with id " + idEducator);
        }
        return Boolean.TRUE;
    }

    @PostMapping("/daycares/{idDaycare}/educators")
    public ResponseEntity<?> createEducator(@PathVariable("idDaycare") Long idDaycare, @RequestBody Educator educator) throws DayCareException {
        try {
            educator = educatorRepo.save(educator);
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>(educator, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error when creating daycare!", e);
            return ResponseEntity.noContent().build();
        }
    }
}
