package fr.ablx.daycare.controllers;

import fr.ablx.daycare.errors.DayCareException;
import fr.ablx.daycare.jpa.Parent;
import fr.ablx.daycare.jpa.ParentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ParentController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ParentRepository parentRepo;

    @RequestMapping("/daycares/{idDaycare}/parents/{idParent}")
    public Parent getParent(@PathVariable Long idDaycare, @PathVariable Long idParent) {

        return parentRepo.findParentByIdAndDaycare(idDaycare, idParent);
    }


    @PostMapping("/daycares/{idDaycare}/parents")
    public ResponseEntity<?> createParent(@PathVariable("idDaycare") Long idDaycare, @RequestBody Parent parent) throws DayCareException {
        try {
            parent = parentRepo.save(parent);
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>(parent, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error when creating daycare!", e);
            return ResponseEntity.noContent().build();
        }
    }
}
